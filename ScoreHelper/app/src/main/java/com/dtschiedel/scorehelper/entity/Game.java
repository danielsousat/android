package com.dtschiedel.scorehelper.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by daniel.sousa on 31/12/2015.
 */
public class Game extends SugarRecord implements Serializable {

    private static final long serialVersionUID = 1L;

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


   /* private void writeObject(ObjectOutputStream out) throws IOException {

        SugarSerializeUtil.writeObject(out, this);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        SugarSerializeUtil.readObject(in, this);

    }*/

}
