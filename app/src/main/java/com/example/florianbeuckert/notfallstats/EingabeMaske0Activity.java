package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EingabeMaske0Activity extends AppCompatActivity {

    private EditText editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske0);

        editTextID = (EditText) findViewById(R.id.editTextID);
    }

    public void next0Pressed(View v) {
        // TODO: ID != null
        final Intent i = new Intent(this, EingabeMaske1Activity.class);
        i.putExtra("id", editTextID.getText().toString());
        startActivity(i);
    }
}
