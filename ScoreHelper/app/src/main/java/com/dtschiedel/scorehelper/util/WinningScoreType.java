package com.dtschiedel.scorehelper.util;

import android.content.Context;

import com.dtschiedel.scorehelper.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel.sousa on 08/01/2016.
 * <p/>
 * Description:
 */
public enum WinningScoreType implements SpinnerEnum {

    BIGGEST_SCORE(0, R.string.biggest_score),
    LOWEST_SCORE(1, R.string.lowest_score);

    private int code;
    private int stringId;

    WinningScoreType(int code, int stringId) {
        this.code = code;
        this.stringId = stringId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStringId() {
        return stringId;
    }

    public void setStringId(int stringId) {
        this.stringId = stringId;
    }

    public static WinningScoreType fromCode(int code) {

        for (WinningScoreType w : values()) {

            if (w.getCode() == code) {

                return w;
            }
        }

        return null;
    }
}
