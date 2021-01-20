package com.example.predictcovid.ui.country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.predictcovid.R;

public class CovidCountryDetail extends AppCompatActivity {

    TextView tvDetailCountryName, tvDetailTodayCases, tvDetailTotalCases, tvDetailTotalDeaths, tvDetailTodayDeaths,
             tvDetailTotalRecovered, tvDetailTodayRecovered, tvDetailTotalActive, tvDetailTotalCritical;

    ImageView imgCountryFlagDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_detail);

        tvDetailCountryName = findViewById(R.id.tvDetailCountryName);
        tvDetailTodayCases = findViewById(R.id.tvDetailTodayCases);
        tvDetailTotalCases = findViewById(R.id.tvDetailTotalCases);
        tvDetailTotalDeaths = findViewById(R.id.tvDetailTotalDeaths);
        tvDetailTodayDeaths = findViewById(R.id.tvDetailTodayDeaths);
        tvDetailTotalRecovered = findViewById(R.id.tvDetailTotalRecovered);
        tvDetailTodayRecovered = findViewById(R.id.tvDetailTodayRecovered);
        tvDetailTotalActive = findViewById(R.id.tvDetailTotalActive);
        tvDetailTotalCritical = findViewById(R.id.tvDetailTotalCritical);
        imgCountryFlagDetail = findViewById(R.id.imgCountryFlagDetail);

        CovidCountry covidCountry = getIntent().getParcelableExtra("EXTRA_COVID");

        tvDetailCountryName.setText(covidCountry.getmCovidCountry());
        tvDetailTodayCases.setText(covidCountry.getmTodayCases());
        tvDetailTotalCases.setText(Integer.toString(covidCountry.getmCases()));
        tvDetailTotalDeaths.setText(covidCountry.getmDeaths());
        tvDetailTodayDeaths.setText(covidCountry.getmTodayDeaths());
        tvDetailTotalRecovered.setText(covidCountry.getmRecovered());
        tvDetailTodayRecovered.setText(covidCountry.getmTodayRecovered());
        tvDetailTotalActive.setText(covidCountry.getmActive());
        tvDetailTotalCritical.setText(covidCountry.getmCritical());


        Glide.with(this).load(covidCountry.getmFlag()).apply(new RequestOptions().override(240,160)).into(imgCountryFlagDetail);
    }
}