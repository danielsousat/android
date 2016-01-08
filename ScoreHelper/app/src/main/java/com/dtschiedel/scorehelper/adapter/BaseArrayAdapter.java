package com.dtschiedel.scorehelper.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.fragment.BasicAlertDialogFragment;


import java.util.List;

/**
 * Created by daniel.sousa on 05/01/2016.
 */
public abstract class BaseArrayAdapter<T> extends ArrayAdapter<T> {

    public interface OnDeleteButtonClickedListener<T> {

        void onDeleteButtonClicked(T item);
    }

    public interface OnItemClickedListener<T> {

        void onItemClicked(T item);
    }

    protected OnDeleteButtonClickedListener<T> onDeleteButtonClickedListener = null;

    protected OnItemClickedListener<T> onItemClickedListener = null;

    public BaseArrayAdapter(Context context, List<T> objects) {
        super(context, 0, objects);

    }

    public abstract int getLayoutResource();

    protected abstract void setupViewData(T item, View view);


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(getLayoutResource(), parent, false);
        }

        T item = getItem(position);

        view.setTag(item);

        setupDeleteButton(view, item);
        setupEvents(view);

        setupViewData(item, view);

        return view;
    }

    private void setupEvents(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemClicked(v);
            }
        });
    }

    private void itemClicked(View view) {

        if (onItemClickedListener != null) {

            onItemClickedListener.onItemClicked((T) view.getTag());
        }
    }

    private void setupDeleteButton(View view, T item) {

        ImageButton button = (ImageButton)view.findViewById(R.id.deleteButton);

        button.setTag(item);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                deleteButtonClicked(v);
            }
        });
    }

    public void deleteButtonClicked(View view) {

        T itemToBeDeleted = (T) view.getTag();

        Log.d(this.getClass().getName(), "delete button clicked on " + itemToBeDeleted);

        if (onDeleteButtonClickedListener != null) {

            onDeleteButtonClickedListener.onDeleteButtonClicked(itemToBeDeleted);
        }
    }

    public OnDeleteButtonClickedListener<T> getOnDeleteButtonClickedListener() {
        return onDeleteButtonClickedListener;
    }

    public void setOnDeleteButtonClickedListener(OnDeleteButtonClickedListener<T> onDeleteButtonClickedListener) {
        this.onDeleteButtonClickedListener = onDeleteButtonClickedListener;
    }

    public OnItemClickedListener<T> getOnItemClickedListener() {
        return onItemClickedListener;
    }

    public void setOnItemClickedListener(OnItemClickedListener<T> onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
