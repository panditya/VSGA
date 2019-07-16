package com.panditya.vsga;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertAndViewActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BASE_DIR_SUFFIX = "/vsga.notes";
    public static final int REQUEST_CODE_STORAGE = 100;
    public int eventId = 0;
    Toolbar toolbar;
    EditText nameEditText, contentEditText;
    Button saveButton;
    Bundle extras;
    Boolean isEditable = false;
    String fileName = "";
    String tempContent = "";

    public void readFile() {
        String path = Environment.getExternalStorageDirectory() + BASE_DIR_SUFFIX;
    }

    public boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readFile();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_and_view);

        eventId = 1;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameEditText = findViewById(R.id.nameEditText);
        contentEditText = findViewById(R.id.contentEditText);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        extras = getIntent().getExtras();

        if (extras != null) {
            fileName = extras.getString("name");
            nameEditText.setText(fileName);
            getSupportActionBar().setTitle("Edit Note");
        }

        getSupportActionBar().setTitle("Add Note");
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkStoragePermission()) {
                readFile();
            }
        } else {
            readFile();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                eventId = 2;
                break;
        }
    }
}
