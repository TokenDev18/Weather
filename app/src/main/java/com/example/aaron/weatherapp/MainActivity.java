package com.example.aaron.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aaron.weatherapp.WeatherAppData.WeatherData;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {


    private TextView wTextView;
    Button wButton;
    private static final String WEATHER_URL = "http://api.openweathermap.org";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wButton = (Button) findViewById(R.id.weather_button);
        wTextView = (TextView) findViewById(R.id.weather_text);

        wButton.setOnClickListener(onWeatherButtonClicked);
    }

    View.OnClickListener onWeatherButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setUpRetrofit();
        }
    };

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
                wTextView.setText(response.body().getWeather().get(2).getDescription());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}

