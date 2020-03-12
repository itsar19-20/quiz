package Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;

import pro.team.ctfly.R;

public class AddFriendDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle onSavedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vw = inflater.inflate(R.layout.add_friend_dialog_body, null);
        builder.setView(vw)
                .setPositiveButton("Chiudi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddFriendDialog.this.getDialog().dismiss();
                    }
                });
        EditText filter = vw.findViewById(R.id.searchingnewfriend);
        //DA COMPLETARE CON IL COLLEGAMENTO AL DATABASE
        return builder.create();
    }

}
