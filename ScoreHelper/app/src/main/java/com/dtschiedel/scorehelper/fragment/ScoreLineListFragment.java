package com.dtschiedel.scorehelper.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.BaseArrayAdapter;
import com.dtschiedel.scorehelper.adapter.BaseListAdapter;
import com.dtschiedel.scorehelper.adapter.ScoreLineAdapter;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.dtschiedel.scorehelper.util.ChildrenEntityManagerContainer;
import com.dtschiedel.scorehelper.util.Util;

import java.util.List;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class ScoreLineListFragment extends BaseListFragment<ScoreLine> {

    private static final String ITEMS_KEY = "items";

    private ChildrenEntityManager<ScoreLine, Game> getChildrenManager() {

        ChildrenEntityManager manager = Util.getParentChildrenManager(this);

        return manager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseListAdapter<ScoreLine> instantianteAdapter(Context context, List<ScoreLine> itens) {
        return new ScoreLineAdapter(context, itens);
    }

    @Override
    protected List<ScoreLine> loadItens() {

        return getChildrenManager().getChildren();
    }

    @Override
    protected BaseMaintainEntityDialogFragment<ScoreLine> instantiateMaintainDialog() {

        MaintainScoreLineDialog dlg = new MaintainScoreLineDialog();

        return dlg;
    }

    @Override
    protected void deleteItem(ScoreLine item) {

        getChildrenManager().removeChild(item);

        resetScoreLinesPositions(getChildrenManager().getChildren());
    }

    public void resetScoreLinesPositions(List<ScoreLine> scoreLines) {

        for (int i = 0 ; i < scoreLines.size() ; i++) {

            scoreLines.get(i).setPosition(i+1);
        }
    }

    @Override
    protected String getDeleteMessageItemName(ScoreLine item) {
        return getString(R.string.score_line);
    }
}
