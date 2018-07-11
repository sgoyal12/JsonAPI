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

public class CustomAdapter2 extends ArrayAdapter {
    ArrayList<Photos> photosList;
    LayoutInflater inflater;
    public CustomAdapter2(@NonNull Context context, ArrayList<Photos> photosList) {
        super(context, 0, photosList);
        this.photosList=photosList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photosList.size();
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
        String string=photosList.get(position).getTitle();
        textView.setText(string);
        return output;
    }
}
