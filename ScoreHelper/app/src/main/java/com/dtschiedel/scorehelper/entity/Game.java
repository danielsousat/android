package com.dtschiedel.scorehelper.entity;

import com.orm.SugarRecord;

/**
 * Created by daniel.sousa on 31/12/2015.
 */
public class Game extends SugarRecord {

    private String name;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
