package com.dtschiedel.scorehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;

import java.util.List;

/**
 * Created by daniel.sousa on 05/01/2016.
 */
public abstract class BaseArrayAdapter<T> extends ArrayAdapter<T> {


    public BaseArrayAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
    }

    public abstract int getLayoutResource();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(getLayoutResource(), parent, false);
        }

        T item = getItem(position);

        setViewData(item, view);

        return view;
    }

    protected abstract void setViewData(T item, View view);
}
