package com.panditya.vsga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle Flow", "onCreate");

        Button button = findViewById(R.id.signUpButton);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle Flow", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle Flow", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle Flow", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle Flow", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle Flow", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecycle Flow", "onRestart");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.signUpButton:
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                break;
        }
    }
}
