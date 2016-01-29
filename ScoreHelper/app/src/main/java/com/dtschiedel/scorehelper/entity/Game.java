package com.dtschiedel.scorehelper.entity;

import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.dtschiedel.scorehelper.util.Util;
import com.dtschiedel.scorehelper.util.WinningScoreType;
import static com.orm.SugarContext.getSugarContext;

import com.orm.SugarApp;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;
import com.orm.dsl.Ignore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by daniel.sousa on 31/12/2015.
 */
public class Game extends SugarRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int winningScoreType = WinningScoreType.BIGGEST_SCORE.getCode();

    private long initialScoreValue = 0;

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

    public int getWinningScoreType() {
        return winningScoreType;
    }

    public void setWinningScoreType(int winningScoreType) {
        this.winningScoreType = winningScoreType;
    }

    public WinningScoreType getWinningScoreTypeEnum() {

        return WinningScoreType.fromCode(getWinningScoreType());
    }

    public void setWinningScoreTypeEnum(WinningScoreType type) {

        setWinningScoreType(type.getCode());
    }

    public long getInitialScoreValue() {
        return initialScoreValue;
    }

    public void setInitialScoreValue(long initialScoreValue) {
        this.initialScoreValue = initialScoreValue;
    }

    public List<ScoreLine> getScoreLines() {

        return ScoreLine.find(ScoreLine.class, "game = ?", new String[]{String.valueOf(getId())}, null,
                "position", null);
    }

    public void deleteGameAndChildren() {

        SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
            @Override
            public void manipulateInTransaction() {

                ScoreLine.deleteAll(ScoreLine.class, "game = ?", String.valueOf(getId()));

                delete();
            }
        });
    }

    public static void saveGameAndScoreLines(Game game, ChildrenEntityManager<ScoreLine, Game> scoreLinesManager) {

        Game.save(game);

        if (scoreLinesManager.getChildrenToBeRemovedFromDatabase().size() > 0) {

            ScoreLine.deleteInTx(scoreLinesManager.getChildrenToBeRemovedFromDatabase());
        }

        for (ScoreLine sl : scoreLinesManager.getChildren()) {

            sl.setGame(game);

            ScoreLine.save(sl);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        Util.writeSugarObject(out, this);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        Util.readSugarObject(in, this);

    }
}


