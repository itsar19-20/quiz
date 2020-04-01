package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

public class StartGameActivity extends AppCompatActivity {
private  RatingBar rbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        rbar=findViewById(R.id.ratingBar);
        rbar.setRating((float)4.5);
    }
}
