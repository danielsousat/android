package com.dtschiedel.scorehelper.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.adapter.BaseArrayAdapter;
import com.dtschiedel.scorehelper.adapter.BaseListAdapter;
import com.dtschiedel.scorehelper.adapter.BaseListDragAndDropAdapter;
import com.dtschiedel.scorehelper.adapter.ScoreLineAdapter;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.dtschiedel.scorehelper.util.ChildrenEntityManagerContainer;
import com.dtschiedel.scorehelper.util.Util;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class ScoreLineListFragment extends BaseListFragment<ScoreLine> implements
        BaseListDragAndDropAdapter.DropListener<ScoreLine> {

    private static final String ITEMS_KEY = "items";

    private ChildrenEntityManager<ScoreLine, Game> getChildrenManager() {

        ChildrenEntityManager manager = Util.getParentChildrenManager(this);

        return manager;
    }

    @Override
    protected BaseListAdapter<ScoreLine> instantianteAdapter(Context context, List<ScoreLine> itens) {

        ScoreLineAdapter adapter = new ScoreLineAdapter(context, itens);

        adapter.setDropListener(this);

        return adapter;
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

    @Override
    public void onDrop(ScoreLine dropedItem, ScoreLine destinationItem) {

        List<ScoreLine> scoreLines = getChildrenManager().getChildren();

        Util.doDropOnList(dropedItem, destinationItem, scoreLines);

        resetScoreLinesPositions(scoreLines);

        refreshListView();
    }

    private int getChildIndex(ScoreLine item, List<ScoreLine> list) {

        for (int i = 0 ; i < list.size(); i++) {

            if (Util.entitiesEqual(list.get(i), item)) {

                return i;
            }
        }

        return -1;
    }
}
