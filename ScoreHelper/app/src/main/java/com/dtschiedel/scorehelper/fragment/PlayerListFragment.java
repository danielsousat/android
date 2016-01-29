package com.dtschiedel.scorehelper.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.BaseArrayAdapter;
import com.dtschiedel.scorehelper.adapter.BaseListAdapter;
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
    protected BaseListAdapter<Player> instantianteAdapter(Context context, List<Player> itens) {
        return new PlayerListAdapter(context, itens);
    }

    @Override
    protected List<Player> loadItens() {

        List<Player> players = null;

        players = Player.listAll(Player.class, "name");

        return players;
    }

    @Override
    protected BaseMaintainEntityDialogFragment<Player> instantiateMaintainDialog() {
        return new MaintainPlayerDialog();
    }

    @Override
    protected void deleteItem(Player item) {

        Player.delete(item);
    }

    @Override
    protected String getDeleteMessageItemName(Player item) {
        return getString(R.string.player) + " " + item.getName();
    }
}
