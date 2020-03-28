package Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import DbUtility.DatabaseHelper;
import DbUtility.DbUserLoginAdapter;
import pro.team.ctfly.R;
import pro.team.ctfly.SignupActivity;

public class ProfileFragment extends Fragment {
    ImageButton btnProfile;
    Button btnEdit;
    Button btnLogout;
    private DbUserLoginAdapter dbUserAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_profile, container, false);

         btnProfile=v.findViewById(R.id.btnProfile);
         btnEdit=v.findViewById(R.id.btnEdit);
         btnLogout=v.findViewById(R.id.btnLogout);
         btnProfile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  FragmentTransaction transaction=getFragmentManager().beginTransaction();

                  transaction.replace(R.id.containerfragment,new ProfileImageFragment()).commit();

             }
         });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Modificato con successo",Toast.LENGTH_SHORT).show();

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbUserAdapter=new DbUserLoginAdapter(getActivity());
                dbUserAdapter.open();

                Cursor cursor=dbUserAdapter.getLoggedUser();
                if (cursor!=null && cursor.moveToFirst()){
                    dbUserAdapter.deleteLoggedUser(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                    Log.d("Utente","" + "rimosso");
                    Toast.makeText(getContext(),"Disconnesso",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SignupActivity.class));
                    cursor.close();
                }


            }
        });

        return v;
    }
}
