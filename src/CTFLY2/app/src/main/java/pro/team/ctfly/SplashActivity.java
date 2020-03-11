package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

import DbUtility.DbUserLoginAdapter;

public class SplashActivity extends AppCompatActivity {

    private DbUserLoginAdapter adapt;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        adapt = new DbUserLoginAdapter(this);
        adapt.open();
        cursor = adapt.getLoggedUser();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(cursor.moveToFirst()) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}
