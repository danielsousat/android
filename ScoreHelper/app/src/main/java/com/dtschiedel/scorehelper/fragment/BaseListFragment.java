package com.dtschiedel.scorehelper.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Entity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.BaseArrayAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by daniel.sousa on 05/01/2016.
 *
 * Description:
 *
 */
public abstract class BaseListFragment<T extends Serializable> extends ListFragment
        implements DialogInterface.OnClickListener, BaseMaintainEntityDialogFragment.OnItemSavedListener<T> {

    private static final String ITEM_TO_BE_DELETED_KEY = "itemToBeDeleted";

    protected abstract BaseArrayAdapter<T> instantianteAdapter(Context context, List<T> itens);

    protected abstract List<T> loadItens();

    protected abstract BaseMaintainEntityDialogFragment<T> instantiateMaintainDialog();

    protected abstract void deleteItem(T item);

    protected abstract String getDeleteMessageItemName(T item);

    private T itemToBeDeleted = null;

    private BasicAlertDialogFragment deleteAlertDialog = null;

    public void addButtonClicked(View view) {

        openMaintainDlg(null);

        Log.d(this.getClass().getName(), "add button clicked");
    }

    protected void openMaintainDlg(T item) {

        BaseMaintainEntityDialogFragment<T> dlg = instantiateMaintainDialog();

        dlg.setItem(item);

        dlg.setOnItemSavedListener(this);

        dlg.show(getFragmentManager(), "MaintainEntityDlg");
    }

    protected void refreshListView() {

        BaseArrayAdapter<T> adapter = (BaseArrayAdapter<T>)getListAdapter();

        adapter.clear();

        adapter.addAll(loadItens());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseArrayAdapter<T> adapter = instantianteAdapter(this.getActivity(), loadItens());

        adapter.setOnDeleteButtonClickedListener(
                new BaseArrayAdapter.OnDeleteButtonClickedListener<T>() {

                    @Override
                    public void onDeleteButtonClicked(T item) {

                        deleteButtonClicked(item);
                    }
                }
        );

        adapter.setOnItemClickedListener(new BaseArrayAdapter.OnItemClickedListener<T>() {
            @Override
            public void onItemClicked(T item) {

                itemClicked(item);
            }
        });

        if (savedInstanceState != null) {

            itemToBeDeleted = (T) savedInstanceState.getSerializable(ITEM_TO_BE_DELETED_KEY);
        }

        setListAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (itemToBeDeleted != null) {

            outState.putSerializable(ITEM_TO_BE_DELETED_KEY, itemToBeDeleted);
        }
    }

    protected void openDeleteDialog(T item) {

        String msg = getString(R.string.delete_confirmation_message);

        msg = String.format(msg, getDeleteMessageItemName(item));

        BasicAlertDialogFragment dlg = BasicAlertDialogFragment.newInstance(
                msg,
                getString(R.string.delete_confirmation_title), this);

        dlg.show(getFragmentManager(), "deleteAlertDialog");
    }



    protected void deleteButtonClicked(T item) {

        itemToBeDeleted = item;
        openDeleteDialog(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;

        view = inflater.inflate(R.layout.fragment_list, container, false);

        setupAddButton(view);

        return view;
    }

    private void setupAddButton(View view) {

        FloatingActionButton addButton = (FloatingActionButton)view.findViewById(R.id.addFloatingButton);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addButtonClicked(v);
            }
        });

    }

    public void itemClicked(T item) {


        Log.d(this.getClass().getName(), "item clicked = " + item);

        openMaintainDlg(item);
    }

    /**
     * Control when the user clicks on the Ok Button of the delete alert dialog.
     * @param dialog
     * @param which
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {

        Log.d(this.getClass().getName(), "itemToBeDeleted = " + itemToBeDeleted);

        deleteItem(itemToBeDeleted);

        itemToBeDeleted = null;

        refreshListView();
    }

    @Override
    public void onItemSaved(T item) {

        refreshListView();
    }
}
