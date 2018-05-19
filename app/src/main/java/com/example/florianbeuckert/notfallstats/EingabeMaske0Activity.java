package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EingabeMaske0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske0);
    }

    public void next0Pressed(View v) {
        final Intent i = new Intent(this, EingabeMaske1Activity.class);
        startActivity(i);
    }
}
