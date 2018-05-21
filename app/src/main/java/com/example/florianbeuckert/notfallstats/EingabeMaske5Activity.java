package com.example.florianbeuckert.notfallstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EingabeMaske5Activity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske5);

        editText = (EditText) findViewById(R.id.editTextKommentar);
    }

    public void donePressed(View v) {

    }
}
