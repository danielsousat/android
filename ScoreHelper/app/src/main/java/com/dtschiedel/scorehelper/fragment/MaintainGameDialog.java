package com.dtschiedel.scorehelper.fragment;

import android.view.View;
import android.widget.EditText;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.util.Util;

/**
 * Created by daniel.sousa on 08/01/2016.
 * <p/>
 * Description:
 */
public class MaintainGameDialog extends BaseMaintainEntityDialogFragment<Game> {

    private EditText getGameNameEdit(View view) {

        return (EditText) view.findViewById(R.id.gameName);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.content_maintain_game;
    }

    @Override
    protected void loadData(Game item, View view) {

        EditText et = getGameNameEdit(view);

        et.setText(item.getName());
    }

    @Override
    protected int getEntityName() {
        return R.string.game;
    }

    @Override
    protected boolean validateData(Game item, View view) {

        EditText et = getGameNameEdit(view);

        if (Util.isEmpty(et.getText().toString())) {

            et.setError(getString(R.string.error_name_invalid));
            return false;
        }


        return true;
    }


    @Override
    protected void saveData(Game item, View view) {

        if (item == null) {

            item = new Game();
        }

        item.setName(getGameNameEdit(view).getText().toString());

        Game.save(item);
    }
}
