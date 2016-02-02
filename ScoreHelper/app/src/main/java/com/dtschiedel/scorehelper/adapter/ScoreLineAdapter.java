package com.dtschiedel.scorehelper.adapter;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.util.ItemDragShadowBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class ScoreLineAdapter extends BaseListDragAndDropAdapter<ScoreLine> {


    public ScoreLineAdapter(Context context, List<ScoreLine> objects) {
        super(context, objects);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.content_score_line_list_item;
    }

    @Override
    protected void setupViewData(ScoreLine item, View view) {

        TextView tv = (TextView)view.findViewById(R.id.scoreLinePosition);

        tv.setText(String.valueOf(item.getPosition()));

        tv = (TextView)view.findViewById(R.id.scoreLineName);

        tv.setText(item.getName());

    }


}
