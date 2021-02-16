package lesson6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class Main {

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

        String response = client.newCall(request).execute().body().string();
        System.out.println(response);

    }
}
