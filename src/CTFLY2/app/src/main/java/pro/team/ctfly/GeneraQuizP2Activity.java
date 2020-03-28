package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DbUtility.DatabaseHelper;
import DbUtility.DbUserLoginAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;

public class GeneraQuizP2Activity extends AppCompatActivity {
    private DbUserLoginAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_quiz_p2);
        Bundle bnl = getIntent().getExtras();
        String categoria = bnl.getString("categoria");
        TextView errore = findViewById(R.id.erroreNewChallenge);
        EditText titolo = findViewById(R.id.titoloNewChallenge);
        EditText descrizione = findViewById(R.id.descizioneNewChallenge);
        EditText flag = findViewById(R.id.flagNewChallenge);
        Button crea = findViewById(R.id.btnNewChallenge);
        dbAdapter = new DbUserLoginAdapter(this);
        dbAdapter.open();
        Cursor c = dbAdapter.getLoggedUser();
        c.moveToFirst();
        String creatore = c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
        crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errore.setVisibility(View.INVISIBLE);
                String strTitolo = titolo.getText().toString();
                String strDescrizione = descrizione.getText().toString();
                String strFlag = flag.getText().toString();
                Log.d("CHECK ", "" + strTitolo + " " + strDescrizione + " " + strFlag);
                if(!strTitolo.matches("") && !strDescrizione.matches("") && !strFlag.matches("")) {
                    MyApiEndpointInterface apiService = AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
                    Call<Boolean> call = apiService.addChallenge(strTitolo, strDescrizione, categoria, creatore, strFlag);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()) {
                                Toast.makeText(GeneraQuizP2Activity.this, "Challenge creata con successo!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(GeneraQuizP2Activity.this, HomeActivity.class));
                            } else {
                                errore.setText("È già presente una challenge con lo stesso titolo!");
                                errore.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(GeneraQuizP2Activity.this, "Connessione al server fallita!", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    errore.setText("Completare tutti i campi!");
                    errore.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GeneraQuizP2Activity.this, GeneraQuizActivity.class));
    }
}
