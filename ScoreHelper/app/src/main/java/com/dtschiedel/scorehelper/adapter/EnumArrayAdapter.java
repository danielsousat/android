package com.dtschiedel.scorehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dtschiedel.scorehelper.util.SpinnerEnum;

/**
 * Created by daniel.sousa on 15/01/2016.
 * <p/>
 * Description:
 */
public class EnumArrayAdapter<T extends SpinnerEnum> extends ArrayAdapter<T> {
    public EnumArrayAdapter(Context context, T[] objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        T item = getItem(position);

        TextView tv = (TextView)view.findViewById(android.R.id.text1);

        tv.setText(getContext().getString(item.getStringId()));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {


        View view = convertView;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        T item = getItem(position);

        TextView tv = (TextView)view.findViewById(android.R.id.text1);

        tv.setText(getContext().getString(item.getStringId()));

        return view;

    }
}
