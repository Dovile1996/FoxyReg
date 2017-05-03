package com.example.dovile.foxy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends MainActivity {

    private static final String REGISTER_URL = "http://foxy.byethost14.com/mobile/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        final EditText mUsernameView = (EditText) findViewById(R.id.Vart);
        final EditText mPasswordView = (EditText) findViewById(R.id.Slapt1);
        final EditText  mPassword2View = (EditText) findViewById(R.id.Slapt2);
        final EditText  mEmailView = (EditText) findViewById(R.id.Elp);
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mPassword2View.setError(null);
        mEmailView.setError(null);

        Button registruotis = (Button) findViewById(R.id.button3);

        registruotis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();
                String email = mEmailView.getText().toString();
                EditText password2EditText = (EditText) findViewById(R.id.Slapt2);
                String password2 = password2EditText.getText().toString();

                boolean cancel = false;
                View focusView = null;

                Vartotojas naujasVartotojas = new Vartotojas(mUsernameView.getText().toString(), mEmailView.getText().toString(), mPasswordView.getText().toString());

                if (!isValid(username)) {
                    mUsernameView.setError("Neteisingas vartotojo vardas");
                    focusView = mPasswordView;
                    cancel = true;
                }
                if (!isValid(password)) {
                    mPasswordView.setError("Neteisingas slaptažodis");
                    focusView = mPasswordView;
                    cancel = true;
                }
                if (!isValid2(email)) {
                    mEmailView.setError("Neteisingas el.paštas");
                    focusView = mEmailView;
                    cancel = true;
                }
                if (password2.matches("")) {
                    mPassword2View.setError("Pakartokite slaptažodį");
                    focusView = mPassword2View;
                    cancel = true;
                }
                if (password.matches(username)) {
                    mPasswordView.setError("Prisijungimo vardas ir slaptažodis negali sutapti");
                    focusView = mPasswordView;
                    cancel = true;
                }
                if (!password2.matches("") && !password.matches(password2)) {
                    mPassword2View.setError("Slaptažodžiai nesutampa");
                    focusView = mPassword2View;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                }
                else {
                    registerUser(naujasVartotojas.getVardas(),naujasVartotojas.getSlaptazodis(),naujasVartotojas.getElpastas());
                    Toast.makeText(SignUp.this, naujasVartotojas.getVardas() + "\n" + naujasVartotojas.getElpastas(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
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
    private boolean isValid2(String credentials){
        final String CREDENTIALS_PATTERN2 ="^([0-9a-zA-Z.]+@+[a-z]+.+[a-z])+$";
        Pattern pattern2 =Pattern.compile(CREDENTIALS_PATTERN2);
        Matcher matcher = pattern2.matcher(credentials);
        return matcher.matches();
    }

    private void registerUser(String username, String password, String email) {
        String urlSuffix = "?username="+username+"&password="+password+"&email="+email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUp.this, "Prašome palaukti",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();


                    con.setRequestProperty("Cookie", "__test=7a4d917e220fbf9a55cab3046bd1a3b7; expires=2038 m. sausio 1 d., penktadienis 01:55:55; path=/");
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }

}
