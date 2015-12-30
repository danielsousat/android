package com.example.danielsousa.teste;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    public static final String TAG = DisplayMessageActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        TextView textView = new TextView(this);

        textView.setTextSize(40);
        textView.setText(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content);
        rl.addView(textView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }
}
