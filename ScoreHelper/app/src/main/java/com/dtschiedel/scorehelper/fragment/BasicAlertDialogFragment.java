package com.dtschiedel.scorehelper.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.dtschiedel.scorehelper.R;

/**
 * Created by daniel.sousa on 07/01/2016.
 * <p/>
 * Description:
 */
public class BasicAlertDialogFragment extends DialogFragment {

    private static final String MESSAGE_KEY = "message";
    private static final String TITLE_KEY = "title";

    private String message;

    private String title;

    public static BasicAlertDialogFragment newInstance(String message, String title,
                                                       Fragment okButtonListenerFragment) {

        BasicAlertDialogFragment dlg = new BasicAlertDialogFragment();

        dlg.setMessage(message);
        dlg.setTitle(title);

        if (okButtonListenerFragment instanceof DialogInterface.OnClickListener) {

            dlg.setTargetFragment(okButtonListenerFragment, 0);
        }


        return dlg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {

            setMessage(savedInstanceState.getString(MESSAGE_KEY));
            setTitle(savedInstanceState.getString(TITLE_KEY));
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(MESSAGE_KEY, getMessage());
        outState.putString(TITLE_KEY, getTitle());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(getMessage());
        builder.setTitle(getTitle());

        builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) getTargetFragment());

        builder.setNegativeButton(R.string.cancel, null);


        return builder.create();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
