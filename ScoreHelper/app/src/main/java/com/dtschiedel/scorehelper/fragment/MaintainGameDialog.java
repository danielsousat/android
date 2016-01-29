package com.dtschiedel.scorehelper.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dtschiedel.scorehelper.R;
import com.dtschiedel.scorehelper.activity.ManageScoreLinesActivity;
import com.dtschiedel.scorehelper.adapter.EnumArrayAdapter;
import com.dtschiedel.scorehelper.entity.Game;
import com.dtschiedel.scorehelper.entity.ScoreLine;
import com.dtschiedel.scorehelper.util.ChildrenEntityManager;
import com.dtschiedel.scorehelper.util.Util;
import com.dtschiedel.scorehelper.util.WinningScoreType;

/**
 * Created by daniel.sousa on 08/01/2016.
 * <p/>
 * Description:
 */
public class MaintainGameDialog extends BaseMaintainEntityDialogFragment<Game> {

    private ChildrenEntityManager<ScoreLine, Game> scoreLinesManager = new ChildrenEntityManager<>();

    private static final String ITEMS_KEY = "items";


    private EditText getGameNameEdit(View view) {

        return (EditText) view.findViewById(R.id.gameName);
    }

    private Spinner getWinningScoreTypeSpinner(View view) {

        return (Spinner) view.findViewById(R.id.winningScoreSpinner);
    }

    private EditText getInitialScoreValueEdit(View view) {

        return (EditText) view.findViewById(R.id.initialScoreValue);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.content_maintain_game;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(ITEMS_KEY, getScoreLinesManager());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            setScoreLinesManager((ChildrenEntityManager<ScoreLine, Game>) savedInstanceState.getSerializable(ITEMS_KEY));
        }

        super.onCreate(savedInstanceState);
    }

    public void openManageScoreLinesActivity() {

        if (getScoreLinesManager().getChildren().isEmpty()) {

            getScoreLinesManager().addChild(new ScoreLine(getString(R.string.points), 1, getItem()));
        }

        Intent intent = new Intent(getActivity(), ManageScoreLinesActivity.class);

        intent.putExtra(ManageScoreLinesActivity.ITEMS_KEY, getScoreLinesManager());

        startActivityForResult(intent, ManageScoreLinesActivity.RETURN_SCORE_LINES);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ManageScoreLinesActivity.RETURN_SCORE_LINES) {

            if (resultCode == Activity.RESULT_OK) {

                setScoreLinesManager((ChildrenEntityManager<ScoreLine, Game>) data.getSerializableExtra(ITEMS_KEY));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Spinner spinner = (Spinner) view.findViewById(R.id.winningScoreSpinner);

        ArrayAdapter<WinningScoreType> adapter = new EnumArrayAdapter<WinningScoreType>(getActivity(),
                WinningScoreType.values());

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Button button = (Button) view.findViewById(R.id.manageScoreLinesButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openManageScoreLinesActivity();
            }
        });

        return view;
    }

    @Override
    protected void loadData(Game item, View view) {

        EditText et = getGameNameEdit(view);

        et.setText(item.getName());

        Spinner spinner = getWinningScoreTypeSpinner(view);

        spinner.setSelection(item.getWinningScoreTypeEnum().ordinal());

        et = getInitialScoreValueEdit(view);

        et.setText(String.valueOf(item.getInitialScoreValue()));

        getScoreLinesManager().setChildren(item.getScoreLines());
    }

    @Override
    protected int getEntityName() {
        return R.string.game;
    }

    @Override
    protected boolean validateData(Game item, View view) {

        EditText et = getGameNameEdit(view);

        if (Util.isEmpty(et.getText().toString())) {

            et.setError(getString(R.string.error_name_invalid));
            return false;
        }

        et = getInitialScoreValueEdit(view);

        if (Util.isEmpty(et.getText().toString())) {

            et.setError(getString(R.string.error_initial_score_value_invalid));
            return false;
        }


        return true;
    }


    @Override
    protected void saveData(Game item, View view) {

        item.setName(getGameNameEdit(view).getText().toString());

        item.setWinningScoreTypeEnum((WinningScoreType) getWinningScoreTypeSpinner(view).getSelectedItem());

        item.setInitialScoreValue(Long.valueOf(getInitialScoreValueEdit(view).getText().toString()));

        Game.saveGameAndScoreLines(item, getScoreLinesManager());
    }

    @Override
    protected Game instantiateItem() {
        return new Game();
    }

    public ChildrenEntityManager<ScoreLine, Game> getScoreLinesManager() {
        return scoreLinesManager;
    }

    public void setScoreLinesManager(ChildrenEntityManager<ScoreLine, Game> scoreLinesManager) {
        this.scoreLinesManager = scoreLinesManager;
    }
}
