package com.dtschiedel.scorehelper.entity;

import com.orm.SugarRecord;

/**
 * Created by daniel.sousa on 31/12/2015.
 */
public class Player extends SugarRecord {

    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
