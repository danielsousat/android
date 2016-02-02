package com.dtschiedel.scorehelper;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.orm.SugarApp;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<SugarApp> {

    private SugarApp app;

    public ApplicationTest() {
        super(SugarApp.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();

        app = getApplication();
    }

    public void testOne() {

        
    }
}