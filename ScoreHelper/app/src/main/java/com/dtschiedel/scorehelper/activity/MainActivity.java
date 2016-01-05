package com.dtschiedel.scorehelper.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.util.Util;

public class MainActivity extends AppCompatActivity {

    private static final String GAMES_TAB_TAG = "GamesTab";
    private static final String PLAYERS_TAB_TAG = "PlayersTab";

    private TabHost mTabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        loadTabs();
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

    public void addButtonClicked(View view) {


    }
}
