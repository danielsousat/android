package com.dtschiedel.scorehelper.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.dtschiedel.scorehelper.adapter.PlayerListAdapter;
import com.dtschiedel.scorehelper.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel.sousa on 05/01/2016.
 *
 * Description:
 */
public class PlayerListFragment extends BaseListFragment<Player> {


    @Override
    protected ArrayAdapter<Player> instantianteAdapter(Context context, List<Player> itens) {
        return new PlayerListAdapter(context, itens);
    }

    @Override
    protected List<Player> loadItens() {

        List<Player> players = null;

        players = new ArrayList<>();

        players.add(new Player("jogador 1"));
        players.add(new Player("jogador 2"));

        //players = Player.listAll(Player.class, "name");

        return players;
    }
}
