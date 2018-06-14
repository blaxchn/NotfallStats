package com.example.florianbeuckert.notfallstats.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.florianbeuckert.notfallstats.R;

public class Form3_annotationActivity extends AppCompatActivity {

    private Spinner spinner;
    private CheckBox checkBox;

    private int extra_ID;
    private int extra_AA;
    private int extra_BB;
    private boolean extra_N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);

        getSupportActionBar().setTitle(R.string.header_form);

        Intent i = getIntent();
        extra_ID = i.getIntExtra("id", -1);
        extra_AA = i.getIntExtra("aa", -1);
        extra_BB = i.getIntExtra("bb", -1);
        extra_N = i.getBooleanExtra("n", false);

        spinner = (Spinner) findViewById(R.id.spinner);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.reason_report_was_wrong, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void next3Pressed(View v) {
        String bemerkung = spinner.getSelectedItem().toString();

        Intent i;

        if(checkBox.isChecked()) {
            i = new Intent(this, Form5_commentActivity.class);
        } else {
            i = new Intent(this, Form4_codeActualActivity.class);
        }

        i.putExtra("id", extra_ID);
        i.putExtra("aa", extra_AA);
        i.putExtra("bb", extra_BB);
        i.putExtra("n", extra_N);
        i.putExtra("bemerkung", bemerkung);

        startActivity(i);
    }
}
