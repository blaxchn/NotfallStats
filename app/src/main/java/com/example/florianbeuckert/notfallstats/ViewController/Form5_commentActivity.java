package com.example.florianbeuckert.notfallstats.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florianbeuckert.notfallstats.Data.Dataset;
import com.example.florianbeuckert.notfallstats.Data.EmergencyCode;
import com.example.florianbeuckert.notfallstats.Data.MySQLiteHelper;
import com.example.florianbeuckert.notfallstats.R;

import java.util.Date;

public class Form5_commentActivity extends AppCompatActivity {

    private EditText editText;

    private int extra_ID;
    private int extra_AA;
    private int extra_BB;
    private boolean extra_N;
    private String extra_Bemerkung;
    private int extra_AA_korrekt;
    private int extra_BB_korrekt;
    private boolean extra_N_korrekt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form5);

        getSupportActionBar().setTitle(R.string.header_maske);

        Intent i = getIntent();
        extra_ID = i.getIntExtra("id", -1);
        extra_AA = i.getIntExtra("aa", -1);
        extra_BB = i.getIntExtra("bb", -1);
        extra_N = i.getBooleanExtra("n", false);
        extra_Bemerkung = i.getStringExtra("bemerkung");
        extra_AA_korrekt = i.getIntExtra("aa_korrekt", -1);
        extra_BB_korrekt = i.getIntExtra("bb_korrekt", -1);
        extra_N_korrekt = i.getBooleanExtra("n_korrekt", false);

        editText = (EditText) findViewById(R.id.editTextKommentar);
    }

    public void donePressed(View v) {
        Dataset datensatz = new Dataset();

        datensatz.setId(extra_ID);
        datensatz.setDate(DateFormat.format("dd. MMM yy", new Date()).toString());
        datensatz.setCodeReported(new EmergencyCode(extra_AA, extra_BB, extra_N));

        if (extra_AA_korrekt != -1)
            datensatz.setCodeActual(new EmergencyCode(extra_AA_korrekt, extra_BB_korrekt, extra_N_korrekt));

        if (extra_Bemerkung != null)
            datensatz.setAnnotation(extra_Bemerkung);
        else
            datensatz.setAnnotation(getString(R.string.keine_bemerkung));

        String kommentar = editText.getText().toString().trim();
        if (!kommentar.equals(""))
            datensatz.setComment(kommentar);
        else
            datensatz.setComment(getString(R.string.kein_kommentar));

        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(getApplicationContext());
        sqLiteHelper.addDataset(datensatz);

        Toast.makeText(getApplicationContext(), "Neuer Eintrag (ID: " + extra_ID + ") wurde angelegt", Toast.LENGTH_LONG).show();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
