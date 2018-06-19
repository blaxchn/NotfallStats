package com.example.florianbeuckert.notfallstats.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florianbeuckert.notfallstats.R;

public class Form4_codeActualActivity extends AppCompatActivity {

    private EditText editTextAA;
    private EditText editTextBB;
    private Button buttonNotarzt;

    private int extra_ID;
    private int extra_AA;
    private int extra_BB;
    private boolean extra_N;
    private String extra_Bemerkung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4);

        getSupportActionBar().setTitle(R.string.header_form);

        Intent i = getIntent();
        extra_ID = i.getIntExtra("id", -1);
        extra_AA = i.getIntExtra("aa", -1);
        extra_BB = i.getIntExtra("bb", -1);
        extra_N = i.getBooleanExtra("n", false);
        extra_Bemerkung = i.getStringExtra("bemerkung");


        editTextAA = (EditText) findViewById(R.id.editTextAA2);
        editTextBB = (EditText) findViewById(R.id.editTextBB2);
        buttonNotarzt = (Button) findViewById(R.id.buttonNotarzt2);
    }

    public void buttonEmDoc2Pressed(View v) {
        if (buttonNotarzt.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary))
            buttonNotarzt.setTextColor(getResources().getColor(R.color.colorDeactivated));
        else
            buttonNotarzt.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void next4Pressed(View v) {
        String aa_string = editTextAA.getText().toString();
        String bb_string = editTextBB.getText().toString();

        int aa = -1;
        int bb = -1;

        try {
            aa = Integer.parseInt(aa_string);
            bb = Integer.parseInt(bb_string);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.info_text_please_enter_valid_code), Toast.LENGTH_LONG).show();
            return;
        }

        boolean n = false;
        if (buttonNotarzt.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary))
            n = true;

        final Intent i = new Intent(this, Form5_commentActivity.class);

        i.putExtra("id", extra_ID);
        i.putExtra("aa", extra_AA);
        i.putExtra("bb", extra_BB);
        i.putExtra("n", extra_N);
        i.putExtra("bemerkung", extra_Bemerkung);
        i.putExtra("aa_korrekt", aa);
        i.putExtra("bb_korrekt", bb);
        i.putExtra("n_korrekt", n);

        startActivity(i);
    }
}
