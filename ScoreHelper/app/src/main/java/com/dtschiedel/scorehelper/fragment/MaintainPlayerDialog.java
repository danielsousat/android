package com.dtschiedel.scorehelper.fragment;

import android.view.View;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.Player;
import com.dtschiedel.scorehelper.util.Util;

/**
 * Created by daniel.sousa on 06/01/2016.
 * <p/>
 * Description:
 */
public class MaintainPlayerDialog extends BaseMaintainEntityDialogFragment<Player> {

    public static MaintainPlayerDialog newInstance(Player item) {
        MaintainPlayerDialog dlg = new MaintainPlayerDialog();

        dlg.setItem(item);

        return dlg;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.content_maintain_player;
    }

    private TextView getNameTextView(View view) {

        return (TextView)view.findViewById(R.id.playerName);
    }

    @Override
    protected void loadData(Player item, View view) {
        getNameTextView(view).setText(item.getName());
    }

    @Override
    protected boolean validateData(Player item, View view) {

        TextView tv = getNameTextView(view);

        String name = getNameTextView(getView()).getText().toString();

        if (Util.isEmpty(name)) {

            tv.setError(getString(R.string.error_name_invalid));
            return false;
        }

        return true;
    }

    @Override
    protected int getEntityName() {
        return R.string.player;
    }

    @Override
    protected void saveData(Player item, View view) {

        item.setName(getNameTextView(view).getText().toString());

        Player.save(item);
    }

    @Override
    protected Player instantiateItem() {
        return new Player();
    }
}
