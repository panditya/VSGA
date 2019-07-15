package com.panditya.vsga;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class PersistentInternalStorageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    EditText editText;
    Button writeFileButton, editFileButton, readFileButton, deleteFileButton;
    FileOutputStream outputStream;

    private static final String FILENAME = "filename.txt";

    private void onWriteFile(String fileContent, String fileName) {
        File path = getDir("NOTES", Context.MODE_PRIVATE);
        File file = new File(path.toString(), fileName);

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(fileContent.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void onEditFile(String fileContent, String fileName) {
        File path = getDir("NOTES", Context.MODE_PRIVATE);
        File file = new File(path.toString(), fileName);

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(fileContent.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void onReadFile(String fileName) {
        File path = getDir("NOTES", Context.MODE_PRIVATE);
        File file = new File(path.toString(), fileName);

        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            textView.setText(text.toString());
        } else {
            textView.setText("");
        }
    }

    private void onDeleteFile(String fileName) {
        File path = getDir("NOTES", Context.MODE_PRIVATE);
        File file = new File(path.toString(), fileName);

        if (file.exists()) {
            file.delete();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistent_internal_storage);

        textView = findViewById(R.id.textView);

        editText = findViewById(R.id.editText);

        writeFileButton = findViewById(R.id.writeFileButton);
        writeFileButton.setOnClickListener(this);

        editFileButton = findViewById(R.id.editFileButton);
        editFileButton.setOnClickListener(this);

        readFileButton = findViewById(R.id.readFileButton);
        readFileButton.setOnClickListener(this);

        deleteFileButton = findViewById(R.id.deleteFileButton);
        deleteFileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.writeFileButton:
                this.onWriteFile("well hello people! this is internal!", FILENAME);
                break;
            case R.id.editFileButton:
                this.onEditFile("caught ya!", FILENAME);
                break;
            case R.id.readFileButton:
                this.onReadFile(FILENAME);
                break;
            case R.id.deleteFileButton:
                this.onDeleteFile(FILENAME);
                break;
        }
    }
}
