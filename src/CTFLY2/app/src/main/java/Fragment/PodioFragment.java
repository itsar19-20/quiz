package Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import CursorAdapter.CursorUserAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import DbUtility.DbUserPunteggioAdapter;
import Model.UserPunteggio;
import Model.Utente;
import pro.team.ctfly.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;

public class PodioFragment extends Fragment {
    private ListView listUsers;
    private DbUserPunteggioAdapter dbUserAdapter;
    private CursorUserAdapter userAdapter;
    private ImageButton searchUserList;
    private EditText searchUser;

    private MyApiEndpointInterface api= AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_podio,container,false);
        listUsers=v.findViewById(R.id.listUser);
        searchUserList=v.findViewById(R.id.searchUserList);
        searchUser=v.findViewById(R.id.searchUser);
        dbUserAdapter=new DbUserPunteggioAdapter(getActivity());
        dbUserAdapter.open();
        Call <List<Utente>> call=api.getAllUser("search");
        call.enqueue(new Callback<List<Utente>>() {
            @Override
            public void onResponse(Call<List<Utente>> call, Response<List<Utente>> response) {
                if(response.body()!=null){
                    Log.d("Connessione" ,"" + response.code());
                    List<Utente> list=response.body();
                    UserPunteggio user;
                    List<UserPunteggio> up=new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        Integer p=list.get(i).getPunteggio();
                        user=new UserPunteggio(list.get(i).getUsername(), p.toString());
                       up.add(user);
                    }
                    dbUserAdapter.insert(up.size(),up);
                    userAdapter=new CursorUserAdapter(getActivity(),dbUserAdapter.getUsers());
                    listUsers.setAdapter(userAdapter);

                }
             searchUserList.setOnClickListener(new View.OnClickListener() {
                 TextView user=v.findViewById(R.id.searchUser);
                 @Override
                 public void onClick(View v) {

                     Cursor c=dbUserAdapter.getSingleUser(user.getText().toString());
                     if (c.moveToFirst()){
                        userAdapter.swapCursor(c);
                         Toast.makeText(getActivity(),"Utente trovato", Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(getActivity(),"Utente non trovato", Toast.LENGTH_SHORT).show();
                         user.setText("");
                     }
                 }
             });
            }


            @Override
            public void onFailure(Call<List<Utente>> call, Throwable t) {
                Log.d("Connesione" ,"" +"fallita");

            }
        });

        return  v;

    }
    @Override
    public void onPause(){
        super.onPause();
        dbUserAdapter.removeAllUsers();

    }
}
