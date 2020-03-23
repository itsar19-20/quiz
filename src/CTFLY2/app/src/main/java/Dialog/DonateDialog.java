package Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import pro.team.ctfly.R;

public class DonateDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle onSavedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vw = inflater.inflate(R.layout.donate_dialog, null);
        TextView soldi = vw.findViewById(R.id.sssoldi);
        ImageButton upBtn = vw.findViewById(R.id.up);
        ImageButton downBtn = vw.findViewById(R.id.down);
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double soldidouble = Double.parseDouble(soldi.getText().toString());
                soldidouble = soldidouble + 5.00;
                soldi.setText(soldidouble.toString());
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double soldidouble = Double.parseDouble(soldi.getText().toString());
                if(soldidouble>=5.00) {
                    soldidouble = soldidouble - 5.00;
                    soldi.setText(soldidouble.toString());
                }
            }
        });
        builder.setView(vw).setTitle("Dona per CTFly!")
                .setPositiveButton("Dona", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Grazie per aver donato!", Toast.LENGTH_SHORT).show();
                        DonateDialog.this.getDialog().dismiss();
                    }
                })
                .setNegativeButton("Cancella", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DonateDialog.this.getDialog().dismiss();
                    }
                });
        return builder.create();
    }
}
