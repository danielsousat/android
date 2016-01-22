package com.dtschiedel.scorehelper.entity;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class ScoreLine extends SugarRecord implements Serializable {

    private static final long serialVersionUID = 3L;

    private String name;

    private int position = 0;

    private Game game;

    public ScoreLine() {
    }

    public ScoreLine(String name, int position, Game game) {
        this.name = name;
        this.position = position;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static int getLastPosition(Game game) {

       return (int)ScoreLine.count(ScoreLine.class, "game = ?", new String[]{String.valueOf(game)});
    }
}
