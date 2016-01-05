package com.dtschiedel.scorehelper.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtschiedel.scorehelper.R;

/**
 * Created by daniel.sousa on 05/01/2016.
 * <p/>
 * Description:
 */
public abstract class BaseMaintainEntityDialogFragment<T> extends DialogFragment {

    private T item = null;

    private boolean editMode = false;


    public BaseMaintainEntityDialogFragment(T item) {

        editMode = item != null;

        this.item = item;
    }

    protected abstract int getLayoutResource();

    protected abstract void loadData(T item, View view);

    protected abstract void validateData(T item);

    protected abstract void saveData(T item);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(getLayoutResource(), null);

        builder.setView(view);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                validateAndSaveData();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dismiss();
            }
        });

        if (isEditMode()) {

            loadData(getItem(), view);
        }

        return builder.create();
    }

    public void validateAndSaveData() {

        try {
            validateData(getItem());
            saveData(getItem());
        } catch (Exception e) {

        }
    }

    public T getItem() {
        return item;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}
