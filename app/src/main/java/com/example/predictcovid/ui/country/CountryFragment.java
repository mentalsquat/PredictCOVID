package com.example.predictcovid.ui.country;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.predictcovid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryFragment extends Fragment {

    RecyclerView rvCovidCountry;
    ProgressBar progressBar;

    CovidCountryAdapter covidCountryAdapter;

    private static final String TAG = CountryFragment.class.getSimpleName();

   // ArrayList<CovidCountry> covidCountries;
    List<CovidCountry> covidCountries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_country, container, false);

       // setHasOptionsMenu(true);

        rvCovidCountry = root.findViewById(R.id.rvCovidCountry);
        progressBar = root.findViewById(R.id.progress_circular_country);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCovidCountry.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        rvCovidCountry.addItemDecoration(dividerItemDecoration);

        covidCountries = new ArrayList<>();

        getDataFromServer();

        return root;
    }

    private void showRecyclerView() {
       // CovidCountryAdapter covidCountryAdapter = new CovidCountryAdapter(covidCountries, getActivity());
        covidCountryAdapter = new CovidCountryAdapter(covidCountries, getActivity());
        rvCovidCountry.setAdapter(covidCountryAdapter);

        ItemClickSupport.addTo(rvCovidCountry).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidCountry(covidCountries.get(position));
            }
        });
    }

    private void showSelectedCovidCountry(CovidCountry covidCountry) {
        Intent covidCovidCountryDetail = new Intent(getActivity(), CovidCountryDetail.class);
        covidCovidCountryDetail.putExtra("EXTRA_COVID", covidCountry);
        startActivity(covidCovidCountryDetail);
    }



    private void getDataFromServer() {
        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse" + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            JSONObject countryInfo = data.getJSONObject("countryInfo");

                            covidCountries.add(new CovidCountry(data.getString("country"), data.getString("todayCases"),
                                    data.getString("deaths"),data.getString("todayDeaths"),data.getString("recovered"),
                                    data.getString("critical"),data.getString("todayRecovered"),data.getString("active"),
                                    countryInfo.getString("flag"), data.getInt("cases")));
                        }
                        Collections.sort(covidCountries, new Comparator<CovidCountry>() {
                            @Override
                            public int compare(CovidCountry o1, CovidCountry o2) {
                                if(o1.getmCases() > o2.getmCases()) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            }
                        });
                        //tvTotalCountry.setText(jsonArray.length() + " krajów");
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse" + error);
            }
    });

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = new SearchView(getActivity());
//        searchView.setQueryHint("Search ...");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if(covidCountryAdapter != null) {
//                    covidCountryAdapter.getFilter().filter(s);
//                }
//                return false;
//            }
//        });
//        searchItem.setActionView(searchView);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}