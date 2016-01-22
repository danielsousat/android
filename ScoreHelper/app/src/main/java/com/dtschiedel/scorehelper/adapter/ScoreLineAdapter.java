package com.dtschiedel.scorehelper.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.entity.ScoreLine;

import java.util.List;

/**
 * Created by daniel.sousa on 18/01/2016.
 * <p/>
 * Description:
 */
public class ScoreLineAdapter extends BaseArrayAdapter<ScoreLine> {

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
