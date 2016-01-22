package com.dtschiedel.scorehelper.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.util.Util;

public class MainActivity extends BaseActivity {

    private static final String GAMES_TAB_TAG = "GamesTab";
    private static final String PLAYERS_TAB_TAG = "PlayersTab";

    private final static String CURRENT_TAB_KEY = "CurrentTab";

    private TabHost mTabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadTabs();
    }

    @Override
    public int getContentLayout() {
        return R.layout.content_main;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {

            mTabHost.setCurrentTab(savedInstanceState.getInt(CURRENT_TAB_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(CURRENT_TAB_KEY, mTabHost.getCurrentTab());
    }

    private void loadTabs() {

        mTabHost = (TabHost) findViewById(R.id.tabHost);

        mTabHost.setup();

        Util.createAndAddTab(mTabHost, GAMES_TAB_TAG, getString(R.string.games), R.id.gameListFragment);
        Util.createAndAddTab(mTabHost, PLAYERS_TAB_TAG, getString(R.string.players), R.id.playerListFragment);

        Util.setTabsLabelStyle(mTabHost);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
