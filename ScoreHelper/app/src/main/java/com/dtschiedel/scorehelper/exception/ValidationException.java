package com.dtschiedel.scorehelper.exception;

import android.view.View;

/**
 * Created by daniel.sousa on 06/01/2016.
 * <p/>
 * Description:
 */
public class ValidationException extends Exception {

    private View view;

    public ValidationException(String detailMessage, View view) {
        super(detailMessage);
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
