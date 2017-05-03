package com.example.dovile.foxy;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.Spinner;

public class NewPost extends SecondActivity {
    private EditText mAmmountView;
    private EditText mPriceView;
    private EditText mLengthView;
    private EditText mWidthView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post);
        Button saugoti = (Button) findViewById(R.id.save);

        mAmmountView = (EditText) findViewById(R.id.kiekis);
        mPriceView = (EditText) findViewById(R.id.kaina);
        mLengthView = (EditText) findViewById(R.id.ilgis);
        mWidthView = (EditText) findViewById(R.id.plotis);

        saugoti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAmmountView.setError(null);
                mPriceView.setError(null);
                mLengthView.setError(null);
                mWidthView.setError(null);

                String ammount = mAmmountView.getText().toString();
                String price = mPriceView.getText().toString();
                String length = mLengthView.getText().toString();
                String width = mWidthView.getText().toString();

                boolean cancel = false;
                View focusView = null;

                if (!isValid(ammount)) {
                    mAmmountView.setError("Neteisingai nurodytas kiekis.");
                    focusView = mAmmountView;
                    cancel = true;
                }
                if (!isValid2(price)) {
                    mPriceView.setError("Neteisingai nurodyta kaina.");
                    focusView = mPriceView;
                    cancel = true;
                }
                if (!isValid2(length)) {
                    mLengthView.setError("Neteisingai nurodytas ilgis.");
                    focusView = mLengthView;
                    cancel = true;
                }
                if (!isValid2(width)) {
                    mWidthView.setError("Neteisingai nurodytas plotis.");
                    focusView = mWidthView;
                    cancel = true;
                }

                final Spinner tipas2 = (Spinner) findViewById(R.id.tipas);
                final EditText kiekisEditText = (EditText) findViewById(R.id.kiekis);
                final Spinner uzsakymai2 = (Spinner) findViewById(R.id.uzsakymai);
                final EditText kainaEditText = (EditText) findViewById(R.id.kaina);
                final Spinner technika2 = (Spinner) findViewById(R.id.technika);
                final EditText ilgisEditText = (EditText) findViewById(R.id.ilgis);
                final EditText plotisEditText = (EditText) findViewById(R.id.plotis);
                final Spinner reguliavimas2 = (Spinner) findViewById(R.id.reguliavimas);

                String sKiekis = kiekisEditText.getText().toString();
                double kiekis2 = Double.parseDouble(sKiekis);
                String sKaina = kainaEditText.getText().toString();
                double kaina2 = Double.parseDouble(sKaina);
                String sIlgis = ilgisEditText.getText().toString();
                double ilgis2 = Double.parseDouble(sIlgis);
                String sPlotis = plotisEditText.getText().toString();
                double plotis2 = Double.parseDouble(sPlotis);

                Rankdarbis irasasRankdarbis= new Rankdarbis (String.valueOf(tipas2.getSelectedItem()),
                        kiekis2, String.valueOf(uzsakymai2.getSelectedItem()),
                        kaina2, String.valueOf(technika2.getSelectedItem()), ilgis2, plotis2, String.valueOf(reguliavimas2.getSelectedItem()));
                /*

                 this.tipas2 = tipas2;
        this.kiekis2 = kiekis2;
        this.uzsakymai2 = uzsakymai2;
        this.kaina2 = kaina2;
        this.technika2 = technika2;
        this.ilgis2 = ilgis2;
        this.plotis2 = plotis2;
        this.reguliavimas2 = reguliavimas2;

                 */
                Toast.makeText(NewPost.this,
                        String.valueOf(tipas2.getSelectedItem()) + "\n" +
                                kiekisEditText.getText() + "\n" +
                                String.valueOf(uzsakymai2.getSelectedItem()) + "\n" +
                                kainaEditText.getText() + "\n" +
                                String.valueOf(technika2.getSelectedItem()) + "\n" +
                                ilgisEditText.getText() + "\n" +
                                plotisEditText.getText() + "\n" +
                                String.valueOf(reguliavimas2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                if (cancel) {
                    focusView.requestFocus();
                }

                else {
                    Intent intent = new Intent(NewPost.this, NewPost.class);
                    startActivity(intent);
                   // Rankdarbis rankdarbis = new Rankdarbis(String tipas2, double kiekis2, String uzsakymai2, double kaina2, String technika2, double ilgis2, double plotis2, double reguliavimas2);
                    }

                }
        });
    }


    private boolean isValid(String credentials) {
        final String CREDENTIALS_PATTERN = "^([0-9])+$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);
        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }

    private boolean isValid2(String credentials) {
        final String CREDENTIALS_PATTERN2 = "^([0-9]+.+[0-9])+$";
        Pattern pattern2 = Pattern.compile(CREDENTIALS_PATTERN2);
        Matcher matcher = pattern2.matcher(credentials);
        return matcher.matches();
    }
}
