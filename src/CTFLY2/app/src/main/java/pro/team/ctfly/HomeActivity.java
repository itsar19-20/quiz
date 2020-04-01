package pro.team.ctfly;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import DbUtility.DbChallengeAdapter;
import Fragment.FriendFragment;
import Fragment.HomeFragment;
import Fragment.PodioFragment;
import Fragment.ProfileFragment;
import Fragment.ShopFragment;


public class HomeActivity extends AppCompatActivity {
    private DbChallengeAdapter dbAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbAdapt = new DbChallengeAdapter(this);
        dbAdapt.open();
        dbAdapt.deleteAllChallenge();
        BottomNavigationView plafoniera = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment, new HomeFragment()).commit();
        plafoniera.setSelectedItemId(R.id.homepage);
        plafoniera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.homepage:
                        dbAdapt.deleteAllChallenge();
                        menuItem.setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment, new HomeFragment()).commit();
                        break;
                    case R.id.prof:
                        dbAdapt.deleteAllChallenge();
                        menuItem.setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment,new ProfileFragment()).commit();
                        break;
                    case R.id.podium:
                        dbAdapt.deleteAllChallenge();
                        menuItem.setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment,new PodioFragment()).commit();
                        break;
                    case R.id.friends:
                        dbAdapt.deleteAllChallenge();
                        menuItem.setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment, new FriendFragment()).commit();
                        break;
                    case R.id.shops:
                        dbAdapt.deleteAllChallenge();
                        menuItem.setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerfragment, new ShopFragment()).commit();
                        break;
                }
                return false;
            }
        });


    }
}