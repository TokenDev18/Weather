package com.example.aaron.weatherapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aaron.weatherapp.WeatherAppData.WeatherData;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by aaron on 1/5/16.
 */
public class CurrentWeatherFragment extends Fragment {

    private static final String WEATHER_URL = "http://api.openweathermap.org";
    private TextView tempTextView;
    private ImageView weatherImage;
    private double kelvinToCelsius;
    private int celsiusToFahrenheit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.current_weather, container, false);
        tempTextView = (TextView) view.findViewById(R.id.temp_text);
        weatherImage = (ImageView) view.findViewById(R.id.weather_image);

        setUpRetrofit();

        return view;
    }

    public void setUpRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();

        httpClient.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        WeatherApi apiWeather = retrofit.create(WeatherApi.class);

        Call<WeatherData> zip  = apiWeather.getCurrentCityWeather();

        zip.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Response<WeatherData> response, Retrofit retrofit) {
                 tempTextView.setText(Double.toString(response.body().getMain().getTemp()));

                switch(response.body().getWeather().get(0).getIcon()){
                    case "50n":
                        weatherImage.setImageResource(R.drawable.mist_icon);

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
