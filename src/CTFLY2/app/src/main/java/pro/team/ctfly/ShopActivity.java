package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        BottomNavigationView plafoniera = findViewById(R.id.bottom_navigation);
        MenuCreate.createMenu(R.id.shops, this, plafoniera);
    }
}
