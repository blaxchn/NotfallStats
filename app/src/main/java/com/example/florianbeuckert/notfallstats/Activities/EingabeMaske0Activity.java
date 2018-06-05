package com.example.florianbeuckert.notfallstats.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florianbeuckert.notfallstats.R;

public class EingabeMaske0Activity extends AppCompatActivity {

    private EditText editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske0);

        getSupportActionBar().setTitle(R.string.header_maske);

        editTextID = (EditText) findViewById(R.id.editTextID);
    }

    public void next0Pressed(View v) {
        String id_string = editTextID.getText().toString();
        int id = -1;

        try {
            id = Integer.parseInt(id_string);
        } catch (Exception e) {
            Toast.makeText(this, "Bitte eine gültige ID eingeben", Toast.LENGTH_LONG).show();
            return;
        }

        if (id == -1) {
            Toast.makeText(this, "Bitte eine gültige ID eingeben", Toast.LENGTH_LONG).show();
            return;
        }

        final Intent i = new Intent(this, EingabeMaske1Activity.class);

        i.putExtra("id", id);

        startActivity(i);
    }
}
