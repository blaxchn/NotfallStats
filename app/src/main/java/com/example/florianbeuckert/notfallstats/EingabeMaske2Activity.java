package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EingabeMaske2Activity extends AppCompatActivity {

    private TextView gemeldeterCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske2);

        gemeldeterCode = (TextView) findViewById(R.id.textViewMeldungCode);
    }

    public void btnJaPressed(View v) {
        //final Intent i = new Intent(this, EingabeMaske4Activity.class);
        //startActivity(i);
    }

    public void btnNeinPressed(View v) {
        final Intent i = new Intent(this, EingabeMaske3Activity.class);
        startActivity(i);
    }
}
