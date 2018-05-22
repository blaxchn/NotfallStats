package com.example.florianbeuckert.notfallstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        datensatz.setDatum(new Date());
        datensatz.setCodeGemeldet(new Einsatzcode(extra_AA, extra_BB, extra_N));
        if (extra_AA_korrekt != -1)
            datensatz.setCodeRichtig(new Einsatzcode(extra_AA_korrekt, extra_BB_korrekt, extra_N_korrekt));
        datensatz.setBemerkung(extra_Bemerkung);
        datensatz.setKommentar(editText.getText().toString());

        String s = "";
        s += "id=" + extra_ID + "\n";
        s += "meldung=" + new Einsatzcode(extra_AA, extra_BB, extra_N) + "\n";
        s += "bemerkung=" + extra_Bemerkung + "\n";
        s += "korrekt=" + new Einsatzcode(extra_AA_korrekt, extra_BB_korrekt, extra_N_korrekt) + "\n";
        s += "kommentar=" + editText.getText().toString() + "\n";
        System.out.println(s);

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
