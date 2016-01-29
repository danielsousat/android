package com.dtschiedel.scorehelper.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.dtschiedel.scorehelper.util.ChildrenEntityManagerContainer;
import com.dtschiedel.scorehelper.util.Util;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class MaintainScoreLineDialog extends BaseMaintainEntityDialogFragment<ScoreLine> {

    private static final String ITEMS_KEY = "items";

    private ChildrenEntityManager<ScoreLine, Game> getScoreLinesManager() {

        return Util.getParentChildrenManager(this);
    }

    private EditText getScoreLineNameEdit(View view) {

        return ((EditText)view.findViewById(R.id.scoreLineName));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.content_maintain_score_line;
    }

    @Override
    protected int getEntityName() {
        return R.string.score_line;
    }

    @Override
    protected void loadData(ScoreLine item, View view) {

        getScoreLineNameEdit(view).setText(item.getName());
    }

    @Override
    protected boolean validateData(ScoreLine item, View view) {

        EditText et = getScoreLineNameEdit(view);

        if (Util.isEmpty(et.getText().toString())) {

            et.setError(getString(R.string.error_name_invalid));
            return false;
        }

        return true;
    }

    @Override
    protected void saveData(ScoreLine item, View view) {

        item.setName(getScoreLineNameEdit(view).getText().toString());

        if (item.getPosition() == 0) {

            item.setPosition(getScoreLinesManager().getChildren().size() + 1);
        }



        item.setGame(getScoreLinesManager().getParent());

        getScoreLinesManager().addChild(item);
    }

    @Override
    protected ScoreLine instantiateItem() {
        return new ScoreLine();
    }


}
