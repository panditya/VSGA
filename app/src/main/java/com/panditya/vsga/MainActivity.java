package com.panditya.vsga;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.panditya.vsga.user.User;
import com.panditya.vsga.user.UserAdapter;
import com.panditya.vsga.user.UserRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MenuInflater menuInflater;
    FloatingActionButton addFab;
    ListView usersListView;
    UserRepository userRepository;
    UserAdapter userAdapter;
    ArrayList usersList;

    protected void onLoad(ArrayList users) {
        userAdapter = new UserAdapter(this, 0, users);
        userAdapter.notifyDataSetChanged();
        usersListView.setAdapter(userAdapter);
    }

    protected void onAlert(final User user) {
        new AlertDialog
                .Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete " + user.getName() + " ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userRepository.delete(user);
                        Toast.makeText(MainActivity.this, user.getName() + " successfully deleted.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();

        this.onLoad(usersList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRepository = new UserRepository(this);
        usersList = userRepository.getAll(null, null);

        usersListView = findViewById(R.id.usersListView);

        usersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User)parent.getAdapter().getItem(position);

                onAlert(user);

                return true;
            }
        });

        addFab = findViewById(R.id.addFab);
        addFab.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.onLoad(usersList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addFab:
                startActivity(new Intent(MainActivity.this, AddDataActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orderAscMenuItem:
                this.onLoad(userRepository.getAll("ASC", null));
                break;
            case R.id.orderDescMenuItem:
                this.onLoad(userRepository.getAll("DESC", null));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
