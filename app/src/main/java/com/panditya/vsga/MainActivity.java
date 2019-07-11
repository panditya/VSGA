package com.panditya.vsga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        final String[] countriesName = new String[]{"Indonesia", "Singapore", "Australia", "Japan"};

        ArrayAdapter<String> listViewDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                countriesName
        );
        listView.setAdapter(listViewDataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, countriesName[position], Toast.LENGTH_LONG).show();
            }
        });

    }

}
