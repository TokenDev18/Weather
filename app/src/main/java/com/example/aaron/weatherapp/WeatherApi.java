package com.example.aaron.weatherapp;



import com.example.aaron.weatherapp.WeatherAppData.WeatherData;

import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by aaron on 12/29/15.
 */
public interface WeatherApi {

    @GET("/data/2.5/weather?zip=94040,us&APPID=e8060058ae43938791ea026093e2c8da")
    Call<WeatherData> getCurrentCityWeather();
}

