package com.dtschiedel.scorehelper.entity;

import com.dtschiedel.scorehelper.util.Util;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by daniel.sousa on 31/12/2015.
 */
public class Player extends SugarRecord implements Serializable {

    private static final long serialVersionUID = 2L;


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

    private void writeObject(ObjectOutputStream out) throws IOException {

        Util.writeSugarObject(out, this);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        Util.readSugarObject(in, this);

    }

}
