package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import DbUtility.DatabaseHelper;
import DbUtility.DbUserLoginAdapter;
import Model.Utente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;


public class SplashActivity extends AppCompatActivity {

    private DbUserLoginAdapter adapt;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        adapt = new DbUserLoginAdapter(this);
        adapt.open();
        cursor = adapt.getLoggedUser();
        if(cursor.moveToFirst()) {
            Log.d("CHECKUSERNAME ", ""+ cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
            MyApiEndpointInterface apiService = AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
            Call<Utente> call = apiService.getUserLogin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)), cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
            call.enqueue(new Callback<Utente>() {
                @Override
                public void onResponse(Call<Utente> call, Response<Utente> response) {
                    Utente u = response.body();
                    Log.d("CHECK1", "PASS");
                    if(u!=null) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        adapt.deleteLoggedUser(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                    }
                }
                @Override
                public void onFailure(Call<Utente> call, Throwable t) {
                    Log.d("CHECKFAIL ", "STOP");
                    startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                    Toast.makeText(SplashActivity.this, "Connessione al server fallita", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Log.d("CHECK2 ", "PASS");
            startActivity(new Intent(getApplicationContext(), SignupActivity.class));
        }
        finish();
    }
}

