package com.panditya.vsga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.panditya.vsga.student.Student;
import com.panditya.vsga.student.StudentRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button storeButton, viewAllStudentButton;
    EditText studentNameEditText, studentHobbyEditText, studentAddressEditText;
    TextView studentsTextView;
    StudentRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new StudentRepository(this);

        studentsTextView = findViewById(R.id.studentsTextView);

        studentNameEditText = findViewById(R.id.studentNameEditText);
        studentHobbyEditText = findViewById(R.id.studentHobbyEditText);
        studentAddressEditText = findViewById(R.id.studentAddressEditText);

        storeButton = findViewById(R.id.storeButton);
        storeButton.setOnClickListener(this);

        viewAllStudentButton = findViewById(R.id.viewAllStudentButton);
        viewAllStudentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.storeButton:
                Log.d("INSET_STUDENT", "onClick: " + studentNameEditText.getText().toString());
                repository.insert(new Student(0, studentNameEditText.getText().toString(), studentHobbyEditText.getText().toString(), studentAddressEditText.getText().toString()));
                break;
            case R.id.viewAllStudentButton:
                ArrayList<Student> students = repository.getAllStudnetsList();
                for (int i = 0; i < students.size(); i++) {
                    studentsTextView.setText(studentsTextView.getText().toString() + ", " + students.get(i));
                    Log.d("student", students.get(i).toString());
                }
                break;
        }
    }
}
