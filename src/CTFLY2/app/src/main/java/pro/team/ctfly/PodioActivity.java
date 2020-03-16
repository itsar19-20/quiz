package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PodioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podio);
        BottomNavigationView plafoniera = findViewById(R.id.bottom_navigation);
        MenuCreate.createMenu(R.id.podium, this, plafoniera);
    }
}
