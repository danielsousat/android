package com.dtschiedel.scorehelper.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.orm.SugarRecord;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by daniel.sousa on 04/01/2016.
 */
public abstract class Util {

    public static ChildrenEntityManager getParentChildrenManager(Fragment fragment) throws ClassCastException {

        try {

            ChildrenEntityManagerContainer c = (ChildrenEntityManagerContainer) fragment.getActivity();

            return c.getChildrenEntityManager();
        } catch (ClassCastException e) {

            throw new ClassCastException("The parent activity of the fragment must implement "
                + ChildrenEntityManagerContainer.class.getName());
        }
    }

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

    /**
     * Method to be used to reduce the boilerplate code of the writeObject serialization method
     * of Sugar ORM Entities.
     * @param out
     * @param entity
     * @throws IOException
     */
    public static void writeSugarObject(ObjectOutputStream out, SugarRecord entity) throws IOException {

        out.defaultWriteObject();
        out.writeObject(entity.getId());
    }

    /**
     * Method to be used to reduce the boilerplate code of the writeObject serialization method
     * of Sugar ORM Entities.
     * @param in
     * @param entity
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readSugarObject(ObjectInputStream in, SugarRecord entity) throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        entity.setId((Long) in.readObject());

    }

    public static boolean entitiesEqual(SugarRecord e1, SugarRecord e2) {

        return e1.getId().equals(e2.getId());
    }

    public static <T extends SugarRecord> void doDropOnList(T dropedItem, T destinationItem, List<T> list) {

        if (Util.entitiesEqual(dropedItem, destinationItem)) {

            return;
        }

        int draggedItemInitialIndex = getChildIndex(dropedItem, list);
        int destinationItemInitialIndex = getChildIndex(destinationItem, list);

        list.remove(draggedItemInitialIndex);

        /* If the item is coming from above */
        if (draggedItemInitialIndex < destinationItemInitialIndex) {
            list.add(destinationItemInitialIndex, dropedItem);
        } else {
            /*If coming from below */
            list.add(getChildIndex(destinationItem, list), dropedItem);
        }


    }

    public static <T extends SugarRecord> int getChildIndex(T item, List<T> list) {

        for (int i = 0 ; i < list.size(); i++) {

            if (Util.entitiesEqual(list.get(i), item)) {

                return i;
            }
        }

        return -1;
    }
}
