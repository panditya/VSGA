package com.panditya.vsga;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public boolean isClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button okButton = findViewById(R.id.button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("buttonOnClickHandled!", "well hello people!");
                if (isClicked == true) {
                    okButton.setText("Hohoho ya found me");
                } else {
                    okButton.setText("I tho ya know it");
                }
                isClicked = !isClicked;
            }
        });
    }
}
