package com.dtschiedel.scorehelper.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.dtschiedel.scorehelper.R;

/**
 * Created by daniel.sousa on 01/02/2016.
 * <p/>
 * Description:
 */
public class ItemDragShadowBuilder extends View.DragShadowBuilder {

    private Integer xTouch = 0;

    private Integer yTouch = 0;

    public ItemDragShadowBuilder(View view) {
        super(view);
    }

    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {

        super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);

        if (xTouch != null && yTouch != null) {

            shadowTouchPoint.set(xTouch, yTouch);
        }
    }

    @Override
    public void onDrawShadow(Canvas canvas) {

        Paint paint = new Paint();

        paint.setColor(ContextCompat.getColor(getView().getContext(), R.color.colorAccent));

        /*
            Drawing just the borders of a rectangle around the shadow.
         */
        canvas.drawLine(0, 0, getView().getWidth(), 0, paint); //top line
        canvas.drawLine(0, getView().getHeight()-1, getView().getWidth(), getView().getHeight()-1, paint); // bottom line
        canvas.drawLine(0, 0, 0, getView().getHeight(), paint); //left line
        canvas.drawLine(getView().getWidth()-1, 0, getView().getWidth()-1, getView().getHeight(), paint); // right line


        super.onDrawShadow(canvas);
    }

    public void setTouchPoint(int x, int y) {

        xTouch = x;
        yTouch = y;
    }

}
