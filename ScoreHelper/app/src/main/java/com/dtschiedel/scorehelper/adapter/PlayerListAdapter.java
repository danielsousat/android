package com.dtschiedel.scorehelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.Player;

import java.util.List;

/**
 * Created by daniel.sousa on 05/01/2016.
 */
public class PlayerListAdapter extends BaseListAdapter<Player> {


    public PlayerListAdapter(Context context, List<Player> objects) {
        super(context, objects);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.content_player_list_item;
    }

    @Override
    protected void setupViewData(Player item, View view) {

        TextView tv = (TextView) view.findViewById(R.id.playerName);

        tv.setText(item.getName());
    }

}
