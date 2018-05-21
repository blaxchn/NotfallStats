package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class EingabeMaske3Activity extends AppCompatActivity {

    private Spinner spinner;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske3);

        spinner = (Spinner) findViewById(R.id.spinner);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grund_fehlmeldung, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void next3Pressed(View v) {
        final Intent i = new Intent(this, EingabeMaske4Activity.class);
        startActivity(i);
    }
}
