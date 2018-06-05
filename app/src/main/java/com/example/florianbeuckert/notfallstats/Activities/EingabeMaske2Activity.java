package com.example.florianbeuckert.notfallstats.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.florianbeuckert.notfallstats.Data.Einsatzcode;
import com.example.florianbeuckert.notfallstats.R;

public class EingabeMaske2Activity extends AppCompatActivity {

    private TextView gemeldeterCode;

    private int extra_ID;
    private int extra_AA;
    private int extra_BB;
    private boolean extra_N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe_maske2);

        getSupportActionBar().setTitle(R.string.header_maske);

        Intent i = getIntent();
        extra_ID = i.getIntExtra("id", -1);
        extra_AA = i.getIntExtra("aa", -1);
        extra_BB = i.getIntExtra("bb", -1);
        extra_N = i.getBooleanExtra("n", false);

        gemeldeterCode = (TextView) findViewById(R.id.textViewMeldungCode);
        gemeldeterCode.setText(new Einsatzcode(extra_AA, extra_BB, extra_N).toString());
    }

    public void btnJaPressed(View v) {
        final Intent i = new Intent(this, EingabeMaske5Activity.class);

        i.putExtra("id", extra_ID);
        i.putExtra("aa", extra_AA);
        i.putExtra("bb", extra_BB);
        i.putExtra("n", extra_N);

        startActivity(i);
    }

    public void btnNeinPressed(View v) {
        final Intent i = new Intent(this, EingabeMaske3Activity.class);

        i.putExtra("id", extra_ID);
        i.putExtra("aa", extra_AA);
        i.putExtra("bb", extra_BB);
        i.putExtra("n", extra_N);

        startActivity(i);
    }
}
