package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EingabeMaske4Activity extends AppCompatActivity {

    EditText editTextAA;
    EditText editTextBB;
    Button buttonNotarzt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske4);

        editTextAA = (EditText) findViewById(R.id.editTextAA2);
        editTextBB = (EditText) findViewById(R.id.editTextBB2);
        buttonNotarzt = (Button) findViewById(R.id.buttonNotarzt2);
    }

    public void buttonNotarzt2Pressed(View v) {

    }

    public void next4Pressed(View v) {
        final Intent i = new Intent(this, EingabeMaske5Activity.class);
        startActivity(i);
    }
}
