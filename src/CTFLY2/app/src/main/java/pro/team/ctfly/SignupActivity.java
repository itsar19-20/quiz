package pro.team.ctfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private DbUserLoginAdapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        adapt = new DbUserLoginAdapter(this);
        final EditText username = findViewById(R.id.username_input);
        EditText password = (EditText) findViewById(R.id.password_input);
        EditText passwordConfirm = (EditText) findViewById(R.id.password_confirm_input);
        final EditText email = findViewById(R.id.email_input);
        TextView passErr = findViewById(R.id.passwordErr);
        TextView goto_login = findViewById(R.id.goto_login);
        Button signup = findViewById(R.id.signup_button);

        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapt.open();
                String strPass = password.getText().toString();
                String strPassConf = passwordConfirm.getText().toString();
                if (strPass.contentEquals(strPassConf)) {
                    Toast.makeText(SignupActivity.this, username.getText().toString() + password.getText().toString() + email.getText().toString(), Toast.LENGTH_SHORT).show();
                    adapt.createUserLogged(username.getText().toString(), password.getText().toString(), email.getText().toString(), "A", 0);
                    startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                    finish();
                } else {
                    passErr.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
