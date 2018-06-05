package com.example.florianbeuckert.notfallstats.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florianbeuckert.notfallstats.R;

public class EingabeMaske1Activity extends AppCompatActivity {

    private EditText editTextAA;
    private EditText editTextBB;
    private Button buttonNotarzt;

    private int extra_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske1);

        getSupportActionBar().setTitle(R.string.header_maske);

        Intent i = getIntent();
        extra_ID = i.getIntExtra("id", -1);

        editTextAA = (EditText) findViewById(R.id.editTextAA);
        editTextBB = (EditText) findViewById(R.id.editTextBB);
        buttonNotarzt = (Button) findViewById(R.id.buttonNotarzt);
        buttonNotarzt.setTextColor(Color.LTGRAY);
    }

    public void buttonNotarztPressed(View v) {
        if (buttonNotarzt.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary))
            buttonNotarzt.setTextColor(getResources().getColor(R.color.colorDeactivated));
        else
            buttonNotarzt.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void next1Pressed(View v) {
        String aa_string = editTextAA.getText().toString();
        String bb_string = editTextBB.getText().toString();

        int aa = -1;
        int bb = -1;

        try {
            aa = Integer.parseInt(aa_string);
            bb = Integer.parseInt(bb_string);
        } catch (Exception e) {
            Toast.makeText(this, "Bitte einen korrekten Code eingeben!", Toast.LENGTH_LONG).show();
            return;
        }

        boolean n = false;
        if (buttonNotarzt.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary))
            n = true;

        final Intent i = new Intent(this, EingabeMaske2Activity.class);

        i.putExtra("id", extra_ID);
        i.putExtra("aa", aa);
        i.putExtra("bb", bb);
        i.putExtra("n", n);

        startActivity(i);
    }
}
