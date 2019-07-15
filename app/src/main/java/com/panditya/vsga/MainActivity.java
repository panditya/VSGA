package com.panditya.vsga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button internalStorageButton, externalStorageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internalStorageButton = findViewById(R.id.internalStorageButton);
        internalStorageButton.setOnClickListener(this);

        externalStorageButton = findViewById(R.id.externalStorageButton);
        externalStorageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.internalStorageButton:
                startActivity(new Intent(this, PersistentInternalStorageActivity.class));
                break;
            case R.id.externalStorageButton:
                startActivity(new Intent(this, PersistentExternalStorageActivity.class));
                break;
        }
    }
}
