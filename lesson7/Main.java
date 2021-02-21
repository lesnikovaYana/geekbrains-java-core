package lesson7;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String CITY = "Санкт-Петербург";

    public static void main(String[] args) throws IOException {

        // write your code here

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("474212_PC")
                .addQueryParameter("apikey", "187qEP4dfnyoBiVQelX7CFqaNLPSM9nP")
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).addHeader("accept", "application/json").build();

        String response = client.newCall(request).execute().body().string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(response).path("DailyForecasts"); //Путь до "DailyForecasts"

        String dateOne = jsonNodeRoot.get(0).get("Date").asText(); //День первый данные
        String temperatureOne = jsonNodeRoot.get(0).path("Temperature").get("Maximum").toString();
        String weatherTextOne = jsonNodeRoot.get(0).get("Day").toString();

        String dateTwo = jsonNodeRoot.get(1).get("Date").asText(); //День второй данные
        String temperatureTwo = jsonNodeRoot.get(1).path("Temperature").get("Maximum").toString();
        String weatherTextTwo = jsonNodeRoot.get(1).get("Day").toString();

        String dateThree = jsonNodeRoot.get(2).get("Date").asText(); //День третий данные
        String temperatureThree = jsonNodeRoot.get(2).path("Temperature").get("Maximum").toString();
        String weatherTextThree = jsonNodeRoot.get(2).get("Day").toString();

        String dateFour = jsonNodeRoot.get(3).get("Date").asText(); //День четвёртый данные
        String temperatureFour = jsonNodeRoot.get(3).path("Temperature").get("Maximum").toString();
        String weatherTextFour = jsonNodeRoot.get(3).get("Day").toString();

        String dateFive = jsonNodeRoot.get(4).get("Date").asText(); //День пятый данные
        String temperatureFive = jsonNodeRoot.get(4).path("Temperature").get("Maximum").toString();
        String weatherTextFive = jsonNodeRoot.get(4).get("Day").toString();

        WeatherResponse weatherOne = new WeatherResponse(dateOne,temperatureOne,weatherTextOne);
        WeatherResponse weatherTwo = new WeatherResponse(dateTwo,temperatureTwo,weatherTextTwo);
        WeatherResponse weatherThree = new WeatherResponse(dateThree,temperatureThree,weatherTextThree);
        WeatherResponse weatherFour = new WeatherResponse(dateFour,temperatureFour,weatherTextFour);
        WeatherResponse weatherFive = new WeatherResponse(dateFive,temperatureFive,weatherTextFive);

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Назовите город:");
            String defaultCity = sc.nextLine();

            System.out.println("Выберите опцию: -получить погоду на 5 дней; - выход;");
            String option = sc.nextLine();
            if (option.equals("получить погоду на 5 дней")) {
                defaultCity = CITY;
                System.out.println("В городе " + defaultCity + " на дату " + weatherOne.getDate() + " ожидается " + weatherOne.getWeatherText() + " температура - " + weatherOne.getTemperature() + " - 1 день.");
                System.out.println("В городе " + defaultCity + " на дату " + weatherTwo.getDate() + " ожидается " + weatherTwo.getWeatherText() + " температура - " + weatherTwo.getTemperature() + " - 2 день.");
                System.out.println("В городе " + defaultCity + " на дату " + weatherThree.getDate() + " ожидается " + weatherThree.getWeatherText() + " температура - " + weatherThree.getTemperature() + " - 3 день.");
                System.out.println("В городе " + defaultCity + " на дату " + weatherFour.getDate() + " ожидается " + weatherFour.getWeatherText() + " температура - " + weatherFour.getTemperature() + " - 4 день.");
                System.out.println("В городе " + defaultCity + " на дату " + weatherFive.getDate() + " ожидается " + weatherFive.getWeatherText() + " температура - " + weatherFive.getTemperature() + " - 5 день.");
            }
            if (option.equals("выход")) {
                System.exit(0);
            }
        }
    }
}
