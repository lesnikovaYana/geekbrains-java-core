package lesson7;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse implements Serializable {
    @JsonProperty(value = "Date")
    private String date;

    @JsonProperty(value = "Maximum")
    private String temperature;

    @JsonProperty(value = "Day")
    private String weatherText;

    public WeatherResponse() {

    }

    public WeatherResponse(String date, String temperature, String weatherText) {
        this.date = date;
        this.temperature = temperature;
        this.weatherText = weatherText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "date='" + date + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weatherText='" + weatherText + '\'' +
                '}';
    }
}
