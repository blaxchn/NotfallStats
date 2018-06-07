package com.example.florianbeuckert.notfallstats.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.florianbeuckert.notfallstats.Data.EmergencyCode;
import com.example.florianbeuckert.notfallstats.R;

public class Form2_codeEvaluateActivity extends AppCompatActivity {

    private TextView gemeldeterCode;

    private int extra_ID;
    private int extra_AA;
    private int extra_BB;
    private boolean extra_N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        getSupportActionBar().setTitle(R.string.header_maske);

        Intent i = getIntent();
        extra_ID = i.getIntExtra("id", -1);
        extra_AA = i.getIntExtra("aa", -1);
        extra_BB = i.getIntExtra("bb", -1);
        extra_N = i.getBooleanExtra("n", false);

        gemeldeterCode = (TextView) findViewById(R.id.textViewMeldungCode);
        gemeldeterCode.setText(new EmergencyCode(extra_AA, extra_BB, extra_N).toString());
    }

    public void btnJaPressed(View v) {
        final Intent i = new Intent(this, Form5_commentActivity.class);

        i.putExtra("id", extra_ID);
        i.putExtra("aa", extra_AA);
        i.putExtra("bb", extra_BB);
        i.putExtra("n", extra_N);

        startActivity(i);
    }

    public void btnNeinPressed(View v) {
        final Intent i = new Intent(this, Form3_annotationActivity.class);

        i.putExtra("id", extra_ID);
        i.putExtra("aa", extra_AA);
        i.putExtra("bb", extra_BB);
        i.putExtra("n", extra_N);

        startActivity(i);
    }
}
