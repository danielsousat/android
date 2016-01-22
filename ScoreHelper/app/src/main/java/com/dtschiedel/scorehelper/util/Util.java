package com.dtschiedel.scorehelper.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;

/**
 * Created by daniel.sousa on 04/01/2016.
 */
public abstract class Util {

    public static TabHost.TabSpec createTab(TabHost tabHost, String tag, String label, int content) {

        TabHost.TabSpec tab = tabHost.newTabSpec(tag);

        tab.setIndicator(label);

        tab.setContent(content);

        return tab;
    }

    public static void createAndAddTab(TabHost tabHost, String tag, String label, int content) {

        tabHost.addTab(createTab(tabHost, tag, label, content));
    }

    public static void setTabsLabelStyle(TabHost tabHost) {

        TabWidget tabWidget = tabHost.getTabWidget();

        for (int i = 0; i < tabWidget.getChildCount(); i++) {

            View v = tabWidget.getChildTabViewAt(i);

            TextView tv = (TextView)v.findViewById(android.R.id.title);

            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }

    }

    public static boolean isEmpty(String string) {

        return string == null || string.isEmpty();
    }
}
