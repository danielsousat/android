package com.dtschiedel.scorehelper.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dtschiedel.scorehelper.R;

import java.util.List;

/**
 * Created by daniel.sousa on 05/01/2016.
 *
 * Description:
 *
 */
public abstract class BaseListFragment<T> extends ListFragment {

    protected abstract ArrayAdapter<T> instantianteAdapter(Context context, List<T> itens);

    protected abstract List<T> loadItens();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<T> adapter = instantianteAdapter(this.getActivity().getApplicationContext(), loadItens());

        setListAdapter(adapter);
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


    public void addButtonClicked(View view) {

        Log.d(this.getClass().getName(), "add button clicked");
    }
}
