package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import DbUtility.DatabaseHelper;
import DbUtility.DbChallengeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;

public class GameActivity extends AppCompatActivity {
Button invia;
EditText flagTest;
DbChallengeAdapter dbChallengeAdapter;
MyApiEndpointInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        invia=findViewById(R.id.inviaFlag);
        flagTest=findViewById(R.id.textFlag);
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbChallengeAdapter=new DbChallengeAdapter(getApplicationContext());
                dbChallengeAdapter.open();
                api= AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
                String flag = "flag";
                Bundle extras=getIntent().getExtras();
               String titolo= extras.getString("titolo");


                    Call<Boolean> call = api.getChallengeSolution(titolo,flagTest.getText());
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.body()) {
                                Toast.makeText(getApplicationContext(), "Risposta corretta", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(GameActivity.this, SuccessActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "Risposta sbagliata", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Connesione fallita", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        });
    }
}
