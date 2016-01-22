package com.dtschiedel.scorehelper.fragment;

import android.content.Context;
import android.os.Bundle;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.BaseArrayAdapter;
import com.dtschiedel.scorehelper.adapter.ScoreLineAdapter;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.util.ChildrenEntityManager;

import java.util.List;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class ScoreLineListFragment extends BaseListFragment<ScoreLine> {

    private static final String ITEMS_KEY = "items";

    private ChildrenEntityManager<ScoreLine, Game> scoreLinesManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            setScoreLinesManager((ChildrenEntityManager<ScoreLine, Game>) savedInstanceState.getSerializable(ITEMS_KEY));
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(ITEMS_KEY, getScoreLinesManager());
    }

    @Override
    protected BaseArrayAdapter<ScoreLine> instantianteAdapter(Context context, List<ScoreLine> itens) {
        return new ScoreLineAdapter(context, itens);
    }

    @Override
    protected List<ScoreLine> loadItens() {

        return getScoreLinesManager().getChildren();
    }

    @Override
    protected BaseMaintainEntityDialogFragment<ScoreLine> instantiateMaintainDialog() {
        MaintainScoreLineDialog dlg = new MaintainScoreLineDialog();

        dlg.setScoreLinesManager(getScoreLinesManager());

        return dlg;
    }

    @Override
    protected void deleteItem(ScoreLine item) {

        getScoreLinesManager().removeChild(item);
    }

    @Override
    protected String getDeleteMessageItemName(ScoreLine item) {
        return getString(R.string.score_line);
    }

    public ChildrenEntityManager<ScoreLine, Game> getScoreLinesManager() {
        return scoreLinesManager;
    }

    public void setScoreLinesManager(ChildrenEntityManager<ScoreLine, Game> scoreLinesManager) {
        this.scoreLinesManager = scoreLinesManager;
    }
}
