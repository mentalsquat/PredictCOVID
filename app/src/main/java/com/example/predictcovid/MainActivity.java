package com.example.predictcovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView mainText;
    private static final String TAG = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mainText = (TextView) findViewById(R.id.main_text);
        setText();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.calucatorBtn) {
                    Intent startIntend = new Intent(getApplicationContext(), CalculatorActivity.class);
                    startActivity(startIntend);
                }else if(id == R.id.trackerBtn) {
                    Intent startIntendSecond = new Intent(getApplicationContext(), TrackerActivity.class);
                    startActivity(startIntendSecond);
                } else if(id == R.id.saveBtn) {
                    Intent  startIntendThird = new Intent(getApplicationContext(), DataActivity.class);
                    startActivity(startIntendThird);
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setText() {
        String s = "-Gorączka\n" + "-Kaszel\n" + "-Duszność\n" + "-Problemy z oddychaniem\n\n\n" + "Jeśli masz takie objawy bezzwłocznie, telefoniczne powiadom stację sanitarno epidemiologiczną "
                + "lub zgloś się bezpośrednio do oddziału zakażnego lub odziału obserwacyjno zakaźnego";
        mainText.setText(s);
    }
}