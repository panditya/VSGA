package com.panditya.vsga;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    EditText editText;
    Button addButton, deleteButton, viewButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    private static final String NAME_KEY = "nameKey";

    private void onViewButtonClick() {
        String nameValue = sharedPreferences.getString(NAME_KEY, "");
        textView.setText(nameValue);
    }

    private void onAddButtonClick() {
        String nameValue = editText.getText().toString();
        Toast.makeText(this, "New key and value has been added to Shared Preferences!", Toast.LENGTH_SHORT);
        this.onEditSharedPreferences(NAME_KEY, nameValue);
    }

    private  void onDeleteButtonClick() {
        this.onClearSharedPreferences();
        Toast.makeText(this, "Shared Preferences has been cleared!", Toast.LENGTH_SHORT);
    }

    private void onEditSharedPreferences(String key, String value) {
        sharedPreferencesEditor.putString(key, value);
        sharedPreferencesEditor.apply();
    }

    private void onClearSharedPreferences() {
        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        editText = findViewById(R.id.editText);

        viewButton = findViewById(R.id.viewButton);
        viewButton.setOnClickListener(this);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(NAME_KEY, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewButton:
                this.onViewButtonClick();
                break;
            case R.id.addButton:
                this.onAddButtonClick();
                break;
            case R.id.deleteButton:
                this.onDeleteButtonClick();
                break;
        }
    }
}
