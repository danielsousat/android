package com.dtschiedel.scorehelper.fragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.GameListAdapter;
import com.dtschiedel.scorehelper.entity.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends BaseListFragment<Game> {


    @Override
    protected ArrayAdapter<Game> instantianteAdapter(Context context, List<Game> itens) {
        return new GameListAdapter(context, itens);
    }

    @Override
    protected List<Game> loadItens() {

        List<Game> games = null;

        // games = Game.listAll(Game.class, "name");

        games = new ArrayList<Game>();

        games.add(new Game("jogo1"));
        games.add(new Game("jogo2"));

        for (int i = 0; i < 30; i++) {

            games.add(new Game("jogoteste"));
        }


        return games;
    }
}
