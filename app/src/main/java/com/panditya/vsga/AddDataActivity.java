package com.panditya.vsga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.panditya.vsga.user.User;
import com.panditya.vsga.user.UserRepository;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener {

    Button submitButton, cancelButton;
    EditText nameEditText, addressEditText;
    UserRepository userRepository;
    String name, address;

    public void onReset() {
        nameEditText.setText("");
        addressEditText.setText("");
    }

    public void onSubmit(View v) {
        name = nameEditText.getText().toString();
        address = addressEditText.getText().toString();

        User user = new User();
        user.setName(name);
        user.setAddress(address);

        userRepository.insert(user);

        Toast.makeText(this, user.getName() + " successfully added.", Toast.LENGTH_SHORT).show();

        this.onReset();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        userRepository = new UserRepository(this);

        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                this.onSubmit(v);
                break;
            case R.id.cancelButton:
                onBackPressed();
                break;
        }
    }
}
