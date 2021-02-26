package lesson8;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String API_KEY = "187qEP4dfnyoBiVQelX7CFqaNLPSM9nP";
    private static final String HOST = "dataservice.accuweather.com";
    private static String CITY;
    private static String NAME_TOWN;

    public static void main(String[] args) throws IOException {

       Scanner sc = new Scanner(System.in);

        while(true) {
            CITY = searchCities();
            System.out.println("Выберите опцию: - 1 - получить погоду на 5 дней; - 2 - выход;");
            String option = sc.nextLine();
            if (option.equals("1")) {
                List<WeatherResponse> weatherAll = getWeatherResponses();

                DbManager dbManager = DbManager.getInstance(); //создаём таблицу
                dbManager.createTableDB();

                for(WeatherResponse object : weatherAll) { //вносим объекты в БД
                    dbManager.insertDB(object);
                }

                List<WeatherResponse> weatherResponseList = dbManager.getAllTableDB(); //выводим объекты из БД
                for (WeatherResponse object : weatherResponseList) {
                    System.out.println(object.toString());
                }
            }
            if (option.equals("2")) {
                System.exit(0);
            }

        }

    }

    @NotNull
    public static List<WeatherResponse> getWeatherResponses() throws IOException {
        String response = searchWeatherFiveDay();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(response).path("DailyForecasts"); //Путь до "DailyForecasts"

        List<WeatherResponse> weatherAll = new ArrayList<>(); //вносим JSON в объекты
        for (int i = 0; i < 5; i++) {
            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setCity(NAME_TOWN);
            weatherResponse.setLocalDate(jsonNodeRoot.get(i).get("Date").asText());
            weatherResponse.setTemperature(jsonNodeRoot.get(i).path("Temperature").get("Maximum").get("Value").asDouble());
            weatherResponse.setWeatherText(jsonNodeRoot.get(i).get("Day").toString());
            weatherAll.add(weatherResponse);
        }
        return weatherAll;
    }

    @NotNull
    public static String searchWeatherFiveDay() throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http").host(HOST)
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(CITY)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).addHeader("accept", "application/json").build();

        String response = client.newCall(request).execute().body().string();
        return response;
    }

    public static String searchCities() throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Введите название города на английском языке:");

        Scanner scanner = new Scanner(System.in);
        String defaultCity = scanner.nextLine();

        HttpUrl locationUrl = new HttpUrl.Builder()
                .scheme("http").host(HOST)
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q",  defaultCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "Application/Json")
                .url(locationUrl).build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Нет информации о запрашиваемом городе" + response.body().string()
                    + response.code());
        }
        String jsR = response.body().string();

        if (objectMapper.readTree(jsR).size() > 0) {
            String cityName = objectMapper.readTree(jsR).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsR).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найдено " + cityName + "/" + countryName);
        } else throw new IOException("Город не найден");
        NAME_TOWN = objectMapper.readTree(jsR).get(0).at("/LocalizedName").asText();
        return objectMapper.readTree(jsR).get(0).at("/Key").asText();
    }

}



