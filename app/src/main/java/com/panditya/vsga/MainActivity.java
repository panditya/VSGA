package com.panditya.vsga;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView selectedOperatorLabel, resultLabel;
    EditText firstValueText, secondValueText;
    Button summationButton, reductionButton, multiplicationButton, divisionButton, calculateButton, resetButton;
    String selectedOperator;
    Float firstValue, secondValue, resultValue = Float.valueOf(0);

    private void resetColor() {
        summationButton.setBackgroundColor(Color.parseColor("#6BABEB"));
        reductionButton.setBackgroundColor(Color.parseColor("#6BABEB"));
        multiplicationButton.setBackgroundColor(Color.parseColor("#6BABEB"));
        divisionButton.setBackgroundColor(Color.parseColor("#6BABEB"));
    }

    private boolean validate() {
        if (firstValue == null) {
            Log.d("firstValue", "isNull");
            Toast.makeText(MainActivity.this, "Please insert the first value!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (secondValue == null) {
            Log.d("secondValue", "isNull");
            Toast.makeText(MainActivity.this, "Please insert the second value!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (selectedOperator == null) {
            Log.d("operator", "isNull");
            Toast.makeText(MainActivity.this, "Please select the operator!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void onSelectOperator(String operator, String text, Button button) {
        selectedOperator = operator;
        selectedOperatorLabel.setText(text);
        button.setBackgroundColor(Color.WHITE);
    }

    private void onSelectedOperator(TextView label) {
        switch (selectedOperator) {
            case "+":
                resultValue = firstValue + secondValue;
                break;
            case "-":
                resultValue = firstValue - secondValue;
                break;
            case "*":
                resultValue = firstValue * secondValue;
                break;
            case "/":
                resultValue = firstValue / secondValue;
                break;
        }
        label.setText(resultValue.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedOperatorLabel = findViewById(R.id.operatorLabelTextView);
        resultLabel = findViewById(R.id.resultTextView);

        summationButton = findViewById(R.id.summationButton);
        summationButton.setOnClickListener(this);

        reductionButton = findViewById(R.id.reductionButton);
        reductionButton.setOnClickListener(this);

        multiplicationButton = findViewById(R.id.multiplicationButton);
        multiplicationButton.setOnClickListener(this);

        divisionButton = findViewById(R.id.divisionButton);
        divisionButton.setOnClickListener(this);

        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(this);

        firstValueText = findViewById(R.id.firstValueText);
        firstValueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                firstValue = Float.valueOf(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("firstValue", firstValue.toString());
            }
        });

        secondValueText = findViewById(R.id.secondValueText);
        secondValueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                secondValue = Float.valueOf(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("secondValue", secondValue.toString());
            }
        });

    }

    @Override
    public void onClick(View v) {
        this.resetColor();
            switch (v.getId()) {
                case R.id.summationButton:
                    this.onSelectOperator("+", "+", summationButton);
                    break;
                case R.id.reductionButton:
                    this.onSelectOperator("-", "-", reductionButton);
                    break;
                case R.id.multiplicationButton:
                    this.onSelectOperator("*", "x", multiplicationButton);
                    break;
                case R.id.divisionButton:
                    this.onSelectOperator("/", "/", divisionButton);
                    break;
                case R.id.calculateButton:
                    if (this.validate()) {
                        this.onSelectedOperator(resultLabel);
                    }
                    break;
            }
    }
}
