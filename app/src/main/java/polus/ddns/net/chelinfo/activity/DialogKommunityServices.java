package polus.ddns.net.chelinfo.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import polus.ddns.net.chelinfo.utils.ConstantManager;

/**
 * Created by Игорь on 09.12.2016.
 */

public class DialogKommunityServices extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] strings;
        strings = getArguments().getStringArray(ConstantManager.DIALOG_ARRAY);
        builder.setTitle(strings[0])
                .setMessage(strings[1])
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}
