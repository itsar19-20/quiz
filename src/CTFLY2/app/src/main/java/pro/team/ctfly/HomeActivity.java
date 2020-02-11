package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Button btnp;
    Button btnpod;
    Button btnh;
    Button btnf;
    Button btns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnp = findViewById(R.id.prof);
        btnpod = findViewById(R.id.podium);
        btnh = findViewById(R.id.homepage);
        btnf = findViewById(R.id.friends);
        btns = findViewById(R.id.shops);
        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                Toast.makeText(HomeActivity.this, "Profilo", Toast.LENGTH_SHORT).show();
            }
        });
        btnpod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PodioActivity.class));
                Toast.makeText(HomeActivity.this, "Podio", Toast.LENGTH_SHORT).show();
            }
        });
        btnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FriendsActivity.class));
                Toast.makeText(HomeActivity.this, "Friends", Toast.LENGTH_SHORT).show();
            }
        });
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ShopActivity.class));
                Toast.makeText(HomeActivity.this, "Shop", Toast.LENGTH_SHORT).show();
            }
        });
    }
}