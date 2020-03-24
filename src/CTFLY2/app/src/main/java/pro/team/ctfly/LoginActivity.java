package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import DbUtility.DbUserLoginAdapter;
import Model.Utente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;

public class LoginActivity extends AppCompatActivity {
    private final static String PREFERENCE = "pref";
    private DbUserLoginAdapter dbAdapt = new DbUserLoginAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn = findViewById(R.id.signup_button);
        final TextView username = findViewById(R.id.username_input);
        final TextView password = findViewById(R.id.password_input);
        final TextView credErr = findViewById(R.id.credenzErr);
        final ProgressBar loading = findViewById(R.id.loadingLogin);
        dbAdapt.open();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                MyApiEndpointInterface apiService = AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
                Call<Utente> call = apiService.getUser(username.getText().toString(), password.getText().toString());
                call.enqueue(new Callback<Utente>() {
                    @Override
                    public void onResponse(Call<Utente> call, Response<Utente> response) {
                        Utente u = response.body();
                        if(u!=null) {
                            dbAdapt.createUserLogged(u.getUsername(), u.getPassword(), u.getEmail(), null, u.getPunteggio());
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            loading.setVisibility(View.INVISIBLE);
                            credErr.setVisibility(View.VISIBLE);
                            username.setText("");
                            password.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Utente> call, Throwable t) {
                        loading.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Connessione al server fallita", Toast.LENGTH_LONG).show();
                        username.setText("");
                        password.setText("");
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}
