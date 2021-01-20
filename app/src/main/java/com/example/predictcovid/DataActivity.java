package com.example.predictcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    private static final String TAG = "DataList";

    DatabaseHelper databaseHelper;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        listView = (ListView) findViewById(R.id.list_view);
        databaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
       // Log.d(TAG, "populate list view");
        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.my_listview, listData);
        listView.setAdapter(adapter);
    }

}