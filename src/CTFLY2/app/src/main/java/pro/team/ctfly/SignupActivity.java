package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DbUtility.DbUserLoginAdapter;
import Model.Utente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;

public class SignupActivity extends AppCompatActivity {
    private DbUserLoginAdapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        adapt = new DbUserLoginAdapter(this);
        final EditText username = findViewById(R.id.username_input);
        EditText password = (EditText) findViewById(R.id.password_input);
        EditText passwordConfirm = (EditText) findViewById(R.id.password_confirm_input);
        final EditText email = findViewById(R.id.email_input);
        TextView passErr = findViewById(R.id.passwordErr);
        TextView goto_login = findViewById(R.id.goto_login);
        Button signup = findViewById(R.id.signup_button);

        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapt.open();
                String strPass = password.getText().toString();
                String strPassConf = passwordConfirm.getText().toString();
                if (strPass.contentEquals(strPassConf)) {
                    MyApiEndpointInterface apiService = AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
                    Call<Utente> call = apiService.addUser(username.getText().toString(), email.getText().toString(), password.getText().toString());
                    call.enqueue(new Callback<Utente>() {
                        @Override
                        public void onResponse(Call<Utente> call, Response<Utente> response) {
                            Utente check = response.body();
                            if(check!=null) {
                                adapt.createUserLogged(check.getUsername(), check.getPassword(), check.getEmail(), null, 0);
                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                passErr.setText("Username gia esistente!");
                                passErr.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<Utente> call, Throwable t) {
                            Toast.makeText(SignupActivity.this, "Connessione al server non riuscita", Toast.LENGTH_LONG).show();
                            passErr.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    passErr.setText("Le password non corrispondono!");
                    passErr.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
