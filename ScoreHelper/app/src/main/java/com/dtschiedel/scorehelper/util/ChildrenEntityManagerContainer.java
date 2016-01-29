package com.dtschiedel.scorehelper.util;

import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.orm.SugarRecord;

/**
 * Created by daniel.sousa on 25/01/2016.
 * <p/>
 * @param <T> type of children entities
 * @param <E> type of parent entity
 * Description:
 */
public interface ChildrenEntityManagerContainer<T extends SugarRecord, E extends SugarRecord> {

    ChildrenEntityManager<T, E> getChildrenEntityManager();
}
