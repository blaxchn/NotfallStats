package com.example.florianbeuckert.notfallstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnPressed(View v) {
        final Intent i = new Intent(this, EingabeMaske0Activity.class);
        startActivity(i);
    }
}
