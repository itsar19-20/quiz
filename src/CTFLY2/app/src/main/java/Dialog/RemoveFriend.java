package Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class RemoveFriend extends DialogFragment {
    private boolean check;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bnl = getArguments();
        builder.setMessage("Vuoi davvero rimuovere " + bnl.getString("username"))
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent();
                        check = true;
                        i.putExtra("check", check);
                        i.putExtra("id", bnl.getLong("id"));
                        i.putExtra("username", bnl.getString("username"));
                        getTargetFragment().onActivityResult(getTargetRequestCode(), 1, i);
                        RemoveFriend.this.getDialog().dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent();
                        check = false;
                        i.putExtra("check", check);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), 1, i);
                        RemoveFriend.this.getDialog().dismiss();
                    }
                });
        return builder.create();
    }
}
