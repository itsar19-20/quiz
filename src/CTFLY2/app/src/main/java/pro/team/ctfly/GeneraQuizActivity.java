package pro.team.ctfly;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Model.ChallengeAppSide;

public class GeneraQuizActivity extends AppCompatActivity {
Spinner cat;
private List<ChallengeAppSide> challengeAppSides=new Challenges().getList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_quiz);
        cat=findViewById(R.id.categoriaNewChallenge);
        cat.setAdapter(new ArrayAdapter<ChallengeAppSide>(this,android.R.layout.simple_spinner_item,challengeAppSides));
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Test di prova",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
