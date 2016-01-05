package com.dtschiedel.scorehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.Game;

import java.util.List;

/**
 * Created by daniel.sousa on 04/01/2016.
 */
public class GameListAdapter extends BaseArrayAdapter<Game> {

    public GameListAdapter(Context context, List<Game> objects) {
        super(context, objects);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.content_game_list_item;
    }

    @Override
    protected void setViewData(Game item, View view) {

        TextView tv = (TextView) view.findViewById(R.id.gameName);

        tv.setText(item.getName());
    }
}
