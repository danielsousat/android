package com.dtschiedel.scorehelper.fragment;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.BaseArrayAdapter;
import com.dtschiedel.scorehelper.adapter.GameListAdapter;
import com.dtschiedel.scorehelper.entity.Game;

import java.util.ArrayList;
import java.util.List;

import static com.orm.SugarContext.getSugarContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends BaseListFragment<Game> {


    @Override
    protected BaseArrayAdapter<Game> instantianteAdapter(Context context, List<Game> itens) {
        return new GameListAdapter(context, itens);
    }

    @Override
    protected List<Game> loadItens() {

        List<Game> games = null;

        games = Game.listAll(Game.class, "name");

        return games;
    }

    @Override
    protected BaseMaintainEntityDialogFragment<Game> instantiateMaintainDialog() {

        return new MaintainGameDialog();
    }

    @Override
    protected void deleteItem(Game item) {

        item.deleteGameAndChildren();
    }

    @Override
    protected String getDeleteMessageItemName(Game item) {
        return getString(R.string.game) + " " + item.getName();
    }
}
