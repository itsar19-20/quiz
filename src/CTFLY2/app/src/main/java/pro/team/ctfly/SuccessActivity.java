package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SuccessActivity extends AppCompatActivity {


Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Return Home", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SuccessActivity.this,HomeActivity.class));
            }
        },2000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

    }
}
