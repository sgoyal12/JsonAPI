package com.example.shubham.apiassi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shubham on 7/10/2018.
 */

public class CustomAdapter extends ArrayAdapter {
    ArrayList<Albums> albumsList;
    LayoutInflater inflater;
    public CustomAdapter(@NonNull Context context, ArrayList<Albums> albumsList) {
        super(context, 0, albumsList);
        this.albumsList=albumsList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return albumsList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View output=convertView;
        if(output==null)
        {
            output=inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            TextView tv;
            tv=output.findViewById(android.R.id.text1);
            output.setTag(tv);
        }
        TextView textView= (TextView) output.getTag();
        String string=albumsList.get(position).getTitle();
        textView.setText(string);
        return output;
    }
}
