package lesson7;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String CITY = "Moscow"; //заменить значение

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
                .addQueryParameter("language", "en-us")
                .addQueryParameter("metric", "true")
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json")
                .build();

        String response = client.newCall(request).execute().body().string(); //вот здесь

        Scanner sc = new Scanner(System.in);

        while(true) {
            ObjectMapper objectMapper = new ObjectMapper();
            //List<WeatherResponse> weatherList = objectMapper.readValue(response, new TypeReference<List<WeatherResponse>>(){});
            WeatherResponse[] weatherArray = objectMapper.readValue(response, WeatherResponse[].class);
            //WeatherResponse weather = objectMapper.readValue(response, WeatherResponse.class);

            System.out.println("Назовите город:");
            String defaultCity = sc.nextLine();

            System.out.println("Выберите опцию: -получить погоду на 5 дней; - выход;");
            String option = sc.nextLine();
            if (option.equals("получить погоду на 5 дней")) {
                defaultCity = CITY;
                System.out.println("1.Прогноз погоды в " + defaultCity);
                System.out.println("2.Прогноз погоды в " + defaultCity);
                System.out.println("3.Прогноз погоды в " + defaultCity);
                System.out.println("4.Прогноз погоды в " + defaultCity);
                System.out.println("5.Прогноз погоды в " + defaultCity);
            }
            if (option.equals("выход")) {
                System.exit(0);
            }
        }
    }
}
