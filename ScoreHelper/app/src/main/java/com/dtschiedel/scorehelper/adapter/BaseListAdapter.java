package com.dtschiedel.scorehelper.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.dtschiedel.scorehelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel.sousa on 25/01/2016.
 * <p/>
 * Description:
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    public interface OnDeleteButtonClickedListener<T> {

        void onDeleteButtonClicked(T item);
    }

    public interface OnItemClickedListener<T> {

        void onItemClicked(T item);
    }

    protected OnDeleteButtonClickedListener<T> onDeleteButtonClickedListener = null;

    protected OnItemClickedListener<T> onItemClickedListener = null;

    protected List<T> items = new ArrayList<>();

    protected Context context;

    public abstract int getLayoutResource();

    protected abstract void setupViewData(T item, View view);

    public BaseListAdapter(Context context, List<T> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;

        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
