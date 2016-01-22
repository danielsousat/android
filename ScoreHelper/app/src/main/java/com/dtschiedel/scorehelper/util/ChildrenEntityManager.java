package com.dtschiedel.scorehelper.util;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel.sousa on 19/01/2016.
 * <p/>
 * 
 * @param <T> type of children entities
 * @param <E> type of parent entity
 *          
 * Description:
 */
public class ChildrenEntityManager<T extends SugarRecord, E extends SugarRecord> implements Serializable {

    private static final long serialVersionUID = 45245245L;

    private E parent = null;
    
    private List<T> childrenToBeRemovedFromDatabase = new ArrayList<T>();

    private List<T> children = new ArrayList<>();

    public void addChild(T object) {

        children.add(object);
    }

    public void removeChild(T child) {

        /* Don't need to keep trace of entities that were not in the database yet */
        if (child.getId() != null) {

            childrenToBeRemovedFromDatabase.add(child);
        }

        children.remove(child);
    }

    public void clear() {

        clearEntitiesToBeRemovedFromDatabase();
    }

    public void clearEntitiesToBeRemovedFromDatabase() {

        childrenToBeRemovedFromDatabase.clear();
    }

    public E getParent() {
        return parent;
    }

    public void setParent(E parent) {
        this.parent = parent;
    }

    public List<T> getChildrenToBeRemovedFromDatabase() {
        return childrenToBeRemovedFromDatabase;
    }

    public void setChildrenToBeRemovedFromDatabase(List<T> childrenToBeRemovedFromDatabase) {
        this.childrenToBeRemovedFromDatabase = childrenToBeRemovedFromDatabase;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
