package Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import DbUtility.DatabaseHelper;
import DbUtility.DbFriendsAdapter;
import DbUtility.DbUserLoginAdapter;
import Model.Utente;
import pro.team.ctfly.HomeActivity;
import pro.team.ctfly.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;

public class AddFriendDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle onSavedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vw = inflater.inflate(R.layout.dialog_add_friend, null);
        builder.setView(vw).setTitle("Aggiungi amico")
                .setPositiveButton("Chiudi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(), -1, null);
                        AddFriendDialog.this.getDialog().dismiss();
                    }
                });
        EditText filter = vw.findViewById(R.id.searchingnewfriend);
        TextView username = vw.findViewById(R.id.findfrienddialog);
        Button addFriendDialog = vw.findViewById(R.id.addfrienddialog);
        ImageButton searchAddFriend = vw.findViewById(R.id.addfriendsearch);
        searchAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApiEndpointInterface apiService = AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
                Call<Utente> call = apiService.getUser(filter.getText().toString());
                call.enqueue(new Callback<Utente>() {
                    @Override
                    public void onResponse(Call<Utente> call, Response<Utente> response) {
                        if (response.body()!=null) {
                            username.setText(response.body().getUsername());
                            Log.d("USERNAMECHECK ", "" + response.body().getUsername());
                            addFriendDialog.setVisibility(View.VISIBLE);
                            addFriendDialog.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DbUserLoginAdapter dbUserAdapt = new DbUserLoginAdapter(getActivity());
                                    dbUserAdapt.open();
                                    Cursor cursor = dbUserAdapt.getLoggedUser();
                                    cursor.moveToFirst();
                                    Integer p = response.body().getPunteggio();
                                    String u = response.body().getUsername();
                                    DbFriendsAdapter dbAdapt = new DbFriendsAdapter(getActivity());
                                    Log.d("CHECKACTIVITY ", ""+ getActivity());
                                    dbAdapt.open();
                                    Call<Boolean> c = apiService.addFriend(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)), u);
                                    c.enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                            if(response.body()) {
                                                dbAdapt.addFriend(u, p.toString());
                                            } else {
                                                Toast.makeText(getActivity(), "Si è verificato un problema", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Boolean> call, Throwable t) {
                                            Log.d("CONNECTION ERROR: ", "connessiona al db fallita");
                                        }
                                    });
                                    getTargetFragment().onActivityResult(getTargetRequestCode(), -1, null);
                                    AddFriendDialog.this.getDialog().dismiss();
                                }
                            });
                        } else {
                            addFriendDialog.setVisibility(View.INVISIBLE);
                            username.setText("Utente non trovato");
                        }
                    }

                    @Override
                    public void onFailure(Call<Utente> call, Throwable t) {
                        Log.d("CONNECTION ERROR: ", "connessiona al db fallita");
                    }
                });
            }
        });
        return builder.create();
    }

}
