package pro.team.ctfly;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuCreate {

    public static void createMenu(int idDefault, Context ss, BottomNavigationView plafoniera) {
        plafoniera.setSelectedItemId(idDefault);
        plafoniera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.prof:
                        ss.startActivity(new Intent(ss, ProfileActivity.class));
                        break;
                    case R.id.podium:
                        ss.startActivity(new Intent(ss, PodioActivity.class));
                        break;
                    case R.id.homepage:
                        ss.startActivity(new Intent(ss, HomeActivity.class));
                        break;
                    case R.id.friends:
                        ss.startActivity(new Intent(ss, FriendsActivity.class));
                        break;
                    case R.id.shops:
                        ss.startActivity(new Intent(ss, ShopActivity.class));
                        break;
                }
                return false;
            }
        });

    }
}
