package com.example.dovile.foxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button prisijungti = (Button) findViewById(R.id.button1);
        Button registruotis = (Button) findViewById(R.id.button2);

        mUsernameView = (EditText) findViewById(R.id.Username);
        mPasswordView = (EditText) findViewById(R.id.Password);

        prisijungti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mUsernameView.setError(null);
                mPasswordView.setError(null);

                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();

                boolean cancel = false;
                View focusView = null;

                if (!isValid(username)) {
                    mUsernameView.setError("Neteisingas vartotojo vardas.");
                    focusView = mPasswordView;
                    cancel = true;
                }
                if (!isValid(password)) {
                    mPasswordView.setError("Neteisingas slaptažodis.");
                    focusView = mPasswordView;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }
        });
            }

    private boolean isValid(String credentials){
        final String CREDENTIALS_PATTERN ="^([0-9a-zA-Z]{3,15})+$";
        Pattern pattern =Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }

    public void signup2 (View v){
        Intent intent = new Intent (MainActivity.this, SignUp.class);
        startActivity(intent);
    }

}
