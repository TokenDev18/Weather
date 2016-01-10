package com.example.aaron.weatherapp;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aaron.weatherapp.WeatherAppData.WeatherData;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {


    //private TextView wTextView;
    private Button forecastButton;
    private Button currentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forecastButton = (Button) findViewById(R.id.forecast_button);
        currentButton = (Button) findViewById(R.id.current_button);
        //wTextView = (TextView) findViewById(R.id.weather_text);

        forecastButton.setOnClickListener(onForecastButtonClicked);
        currentButton.setOnClickListener(onCurrentButtonClicked);
    }

    View.OnClickListener onForecastButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ForecastWeatherFragment fFrag = new ForecastWeatherFragment();
            FragmentTransaction fFragTrans = getFragmentManager().beginTransaction();

            fFragTrans.replace(R.id.view,fFrag);
            fFragTrans.addToBackStack(null);

            fFragTrans.commit();

        }
    };

    View.OnClickListener onCurrentButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CurrentWeatherFragment cFrag = new CurrentWeatherFragment();
            FragmentTransaction cFragTrans = getFragmentManager().beginTransaction();

            cFragTrans.replace(R.id.view, cFrag);
            cFragTrans.addToBackStack(null);

            cFragTrans.commit();
        }
    };
}

