package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneraQuizActivity extends AppCompatActivity {
    private final String[] CATEGORIE = {"Coding", "Security", "Encription"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_quiz);
        Spinner categorie = findViewById(R.id.categoriaNewChallenge);
        Button continua = findViewById(R.id.continuaNewChallenge);
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.addAll(Arrays.asList(CATEGORIE));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_newchallenge_categoria, spinnerArray);
        categorie.setAdapter(adapter);
        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK ", "" + categorie.getSelectedItem().toString());
                Intent i = new Intent(GeneraQuizActivity.this, GeneraQuizP2Activity.class);
                i.putExtra("categoria", categorie.getSelectedItem().toString());
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GeneraQuizActivity.this, HomeActivity.class));
    }
}
