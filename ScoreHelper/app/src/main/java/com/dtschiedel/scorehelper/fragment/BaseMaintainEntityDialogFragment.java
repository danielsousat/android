package com.dtschiedel.scorehelper.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dtschiedel.scorehelper.R;

import java.io.Serializable;

/**
 * Created by daniel.sousa on 05/01/2016.
 * <p/>
 * Description:
 */
public abstract class BaseMaintainEntityDialogFragment<T extends Serializable>
        extends DialogFragment {

    public interface OnItemSavedListener<T> {

        void onItemSaved(T item);
    }

    private static final String ITEM_KEY = "item";

    private T item = null;

    protected abstract int getLayoutResource();

    protected abstract int getEntityName();

    protected abstract void loadData(T item, View view);

    protected abstract boolean validateData(T item, View view);

    /**
     * Method to fill the item with the data on the screen and save it to the database.
     *
     * @param item item to be saved. If it is a new item, this parameter will be null
     * @param view the view from where to extract the data
     */
    protected abstract void saveData(T item, View view);



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {

            setItem((T) savedInstanceState.getSerializable(ITEM_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (item != null) {

            outState.putSerializable(ITEM_KEY, item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutResource(), container, false);

        Button button = (Button)view.findViewById(R.id.okButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateAndSaveData()) {

                    dismiss();
                }

            }
        });

        button = (Button)view.findViewById(R.id.cancelButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });



        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (item != null) {
            loadData(getItem(), view);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dlg = super.onCreateDialog(savedInstanceState);

        String title = isEditMode() ? getString(R.string.edit) : getString(R.string.new_string);

        dlg.setTitle(title + " " + getString(getEntityName()));

        return dlg;
    }

    public boolean validateAndSaveData() {

        boolean ok = validateData(getItem(), getView());

        if (ok) {

            saveData(getItem(), getView());

            OnItemSavedListener<T> listener = (OnItemSavedListener<T>) getTargetFragment();

            if (listener != null) {

                listener.onItemSaved(getItem());
            }
        }

        return ok;

    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isEditMode() {
        return getItem() != null;
    }

    public OnItemSavedListener<T> getOnItemSavedListener() {
        return (OnItemSavedListener<T>) getTargetFragment();
    }

    public void setOnItemSavedListener(OnItemSavedListener<T> onItemSavedListener) {
        setTargetFragment((Fragment)onItemSavedListener, 0);
    }
}
