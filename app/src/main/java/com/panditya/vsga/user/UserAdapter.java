package com.panditya.vsga.user;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.panditya.vsga.R;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private Activity activity;
    private ArrayList<User> users;
    private static LayoutInflater inflater = null;

    public UserAdapter(Activity activity, int textViewResourceId, ArrayList<User> users) {
        super(activity, textViewResourceId, users);

        try {
            this.activity = activity;
            this.users = users;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
            //
        }
    }

    public int getCount() {
        return users.size();
    }

    public User getItem(User position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView address;
    }

    public View getView(int position, View v, ViewGroup parent) {
        View view = v;
        final ViewHolder holder;

        try {
            if (v == null) {
                view = inflater.inflate(R.layout.user_list_layout, null);

                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.name);
                holder.address = (TextView) view.findViewById(R.id.address);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.name.setText(users.get(position).name);
            holder.address.setText(users.get(position).address);

        } catch (Exception e) {
            //
        }

        return view;
    }
}