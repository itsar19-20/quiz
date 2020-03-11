package pro.team.ctfly;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView plafoniera = findViewById(R.id.bottom_navigation);
        plafoniera.setSelectedItemId(R.id.homepage);
        plafoniera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.prof:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        break;
                    case R.id.podium:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, PodioActivity.class));
                        break;
                    case R.id.homepage:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;
                    case R.id.friends:
                        menuItem.setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment, new FriendFragment()).commit();
                        break;
                    case R.id.shops:
                        HomeActivity.this.startActivity(new Intent(HomeActivity.this, ShopActivity.class));
                        break;
                }
                return false;
            }
        });

        //MenuCreate.createMenu(R.id.homepage, this, plafoniera);

    }
}