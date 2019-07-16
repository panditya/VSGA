package com.panditya.vsga;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_STORAGE = 100;
    public static final String BASE_DIR_SUFFIX = "/vsga.notes";
    SimpleDateFormat simpleDateFormat;
    Toolbar toolbar;
    Intent intent;
    ListView notesListView;

    protected void onDeleteItem(String fileName) {
        String path = Environment.getExternalStorageDirectory().toString() + BASE_DIR_SUFFIX;
        File file = new File(path, fileName);

        if (file.exists()) {
            file.delete();
        }

        getFileListOnDirectory();
    }

    public void onDeleteItemConfirmation(final String itemName) {
        new AlertDialog
                .Builder(this)
                .setTitle("Delete this note?")
                .setMessage("Are you sure want to delete this " + itemName + " note?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDeleteItem(itemName);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();

    }

    protected void getFileListOnDirectory() {
        String path = Environment.getExternalStorageDirectory().toString() + BASE_DIR_SUFFIX;
        File baseDir = new File(path);

        if (baseDir.exists()) {
            File[] files = baseDir.listFiles();
            String[] fileNames = new String[files.length];
            String[] createdAt = new String[files.length];
            simpleDateFormat = new SimpleDateFormat("dd MMM YYYY HH:mm:ss");
            ArrayList<Map<String, Object>> dataList = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                fileNames[i] = files[i].getName();
                createdAt[i] = simpleDateFormat.format(new Date(files[i].lastModified()));
                Map<String, Object> listDataMap = new HashMap<>();
                listDataMap.put("name", fileNames[i]);
                listDataMap.put("date", createdAt[i]);
                dataList.add(listDataMap);
            }

            SimpleAdapter dataListAdapter = new SimpleAdapter(
                    this,
                    dataList,
                    android.R.layout.simple_list_item_2,
                    new String[]{"name", "date"},
                    new int[]{android.R.id.text1, android.R.id.text2});

            notesListView.setAdapter(dataListAdapter);
            dataListAdapter.notifyDataSetChanged();
        }
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
                    getFileListOnDirectory();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addActionItem:
                startActivity(new Intent(MainActivity.this, InsertAndViewActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Notes App");

        notesListView = findViewById(R.id.notesListView);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> data = (Map<String, Object>)parent.getAdapter().getItem(position);
                intent = new Intent(MainActivity.this, InsertAndViewActivity.class);
                intent.putExtra("filename", data.get("name").toString());
            }
        });

        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> data = (Map<String, Object>)parent.getAdapter().getItem(position);

                onDeleteItemConfirmation(data.get("name").toString());

                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkStoragePermission()) {
                getFileListOnDirectory();
            }
        } else {
            getFileListOnDirectory();
        }
    }
}
