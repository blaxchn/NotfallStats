package com.example.florianbeuckert.notfallstats.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florianbeuckert.notfallstats.Data.Datensatz;
import com.example.florianbeuckert.notfallstats.Data.Einsatzcode;
import com.example.florianbeuckert.notfallstats.Data.MySQLiteHelper;
import com.example.florianbeuckert.notfallstats.R;

import java.util.Date;

public class EingabeMaske5Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_eingabe_maske5);

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
        Datensatz datensatz = new Datensatz();

        datensatz.setId(extra_ID);
        datensatz.setDatum(DateFormat.format("dd. MMM yy", new Date()).toString());
        datensatz.setCodeGemeldet(new Einsatzcode(extra_AA, extra_BB, extra_N));

        if (extra_AA_korrekt != -1)
            datensatz.setCodeKorrekt(new Einsatzcode(extra_AA_korrekt, extra_BB_korrekt, extra_N_korrekt));

        if (extra_Bemerkung != null)
            datensatz.setBemerkung(extra_Bemerkung);
        else
            datensatz.setBemerkung(getString(R.string.keine_bemerkung));

        String kommentar = editText.getText().toString().trim();
        if (!kommentar.equals(""))
            datensatz.setKommentar(kommentar);
        else
            datensatz.setKommentar(getString(R.string.kein_kommentar));

        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(getApplicationContext());
        sqLiteHelper.addDatensatz(datensatz);

        Toast.makeText(getApplicationContext(), "Neuer Eintrag (ID: " + extra_ID + ") wurde angelegt", Toast.LENGTH_LONG).show();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
