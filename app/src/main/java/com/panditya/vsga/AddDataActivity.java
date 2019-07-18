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

    Bundle bundle;
    Button submitButton, cancelButton;
    EditText nameEditText, addressEditText;
    UserRepository userRepository;
    User user;
    String name, address;

    public void onLoad(Bundle bundle) {
        Integer id = bundle.getInt("id");
        String name = bundle.getString("name");
        String address = bundle.getString("address");

        nameEditText.setText(name);
        addressEditText.setText(address);

        user.setId(id);
        user.setName(name);
        user.setAddress(address);
    }

    public void onReset() {
        nameEditText.setText("");
        addressEditText.setText("");
    }

    public void onSubmit(View v) {
        name = nameEditText.getText().toString();
        address = addressEditText.getText().toString();

        user.setName(name);
        user.setAddress(address);

        if (user.getId() == null) {
            userRepository.insert(user);

            Toast.makeText(this, user.getName() + " successfully added.", Toast.LENGTH_SHORT).show();
        }

        userRepository.update(user);

        Toast.makeText(this, user.getName() + " successfully updated.", Toast.LENGTH_SHORT).show();

        this.onReset();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        bundle = getIntent().getExtras();
        user = new User();
        userRepository = new UserRepository(this);

        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        if (bundle != null) {
            this.onLoad(bundle);
        } else {
            this.onReset();
        }
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
