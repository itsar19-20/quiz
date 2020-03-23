package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pro.team.ctfly.Fragment.ProfileFragment;
import pro.team.ctfly.Fragment.ProfileImageFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView plafoniera = findViewById(R.id.bottom_navigation);
        MenuCreate.createMenu(R.id.prof, this, plafoniera);


    }

}
