package com.dtschiedel.scorehelper.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.fragment.ScoreLineListFragment;
import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.dtschiedel.scorehelper.util.ChildrenEntityManagerContainer;

/**
 * Created by daniel.sousa on 19/01/2016.
 * <p/>
 * Description:
 */
public class ManageScoreLinesActivity extends BaseActivity implements
        ChildrenEntityManagerContainer<ScoreLine, Game> {

    public static final String ITEMS_KEY = "items";

    public static final int RETURN_SCORE_LINES = 0;

    private ChildrenEntityManager<ScoreLine, Game> scoreLinesManager;


    @Override
    public int getContentLayout() {
        return R.layout.content_manage_score_lines;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {

            setScoreLinesManager((ChildrenEntityManager<ScoreLine, Game>) getIntent().getSerializableExtra(ITEMS_KEY));
        }

        initScoreLinesListFragment();

        initToolbar();
    }

    private void initToolbar() {

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setResultOkAndFinish() {
        Intent i = new Intent();
        i.putExtra(ITEMS_KEY, getScoreLinesManager());
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Log.d(getClass().getName(), "Home button clicked");
                setResultOkAndFinish();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void initScoreLinesListFragment() {

        ScoreLineListFragment f = new ScoreLineListFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(R.id.scoreLinesListContainer, f);

        ft.commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {

            setScoreLinesManager((ChildrenEntityManager<ScoreLine, Game>) savedInstanceState.getSerializable(ITEMS_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(ITEMS_KEY, getScoreLinesManager());
    }


    public ChildrenEntityManager<ScoreLine, Game> getScoreLinesManager() {
        return scoreLinesManager;
    }

    public void setScoreLinesManager(ChildrenEntityManager<ScoreLine, Game> scoreLinesManager) {
        this.scoreLinesManager = scoreLinesManager;
    }

    @Override
    public ChildrenEntityManager<ScoreLine, Game> getChildrenEntityManager() {
        return getScoreLinesManager();
    }
}
