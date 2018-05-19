package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EingabeMaske1Activity extends AppCompatActivity {

    private EditText editTextAA;
    private EditText editTextBB;
    private Button buttonNotarzt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske1);

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
        final Intent i = new Intent(this, EingabeMaske2Activity.class);
        startActivity(i);
    }
}
