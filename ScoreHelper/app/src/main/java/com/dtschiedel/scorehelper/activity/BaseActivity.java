package com.dtschiedel.scorehelper.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import com.dtschiedel.scorehelper.R;

/**
 * Created by daniel.sousa on 22/01/2016.
 * <p/>
 * Description: Base activity that uses the app activity template to render standardized activities.
 * All Activities from the app should inherit from this class.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     *
     * @return Returns the id of the main xml layout that will be inflated in the content section of
     * the template.
     */
    public abstract int getContentLayout();

    public int getTemplateLayout() {

        return R.layout.activity_template;
    }

    protected void loadContent() {

        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.templateLayout);

        LayoutInflater inflater = getLayoutInflater();

        inflater.inflate(getContentLayout(), layout, true);
    }

    protected void loadToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getTemplateLayout());

        loadContent();

        loadToolbar();
    }
}
