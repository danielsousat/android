package com.dtschiedel.scorehelper.util;

import com.orm.SugarRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.dtschiedel.scorehelper.util.Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UtilTest {

    private List<SugarRecord> entities = new ArrayList<>();

    @Before
    public  void setUp() {

        for (int i = 0; i < 10 ; i++) {

            entities.add(createEntity(i));
        }
    }

    private SugarRecord createEntity(long id) {

        SugarRecord sr = new SugarRecord();

        sr.setId(id);

        return sr;
    }

    @Test
    public void testDragFirstItemAndDropOnSecondItem() {

        testDrop(0, 1);
    }

    @Test
    public void testDragLastItemAndDropOnTheItemBefore() {

        testDrop(entities.size()-1, entities.size()-2);
    }

    private void testDrop(int draggedItemIndex, int destinationIndex) {

        int destinationStartIndex = entities.size()-2;
        int draggedItemStartIndex = entities.size()-1;

        SugarRecord draggedItem = entities.get(draggedItemStartIndex);
        SugarRecord destinationItem = entities.get(destinationStartIndex);

        Util.doDropOnList(draggedItem, destinationItem, entities);

        Assert.assertEquals(entities.indexOf(draggedItem), destinationStartIndex);
        Assert.assertEquals(entities.indexOf(destinationItem), draggedItemStartIndex);
    }
}