package com.example.florianbeuckert.notfallstats.ViewController;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.example.florianbeuckert.notfallstats.Data.Datensatz;
import com.example.florianbeuckert.notfallstats.Data.MySQLiteHelper;
import com.example.florianbeuckert.notfallstats.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper sqLiteHelper;
    private List<Datensatz> daten = new ArrayList<Datensatz>();

    private TextView tvStatKorrekt, tvStatPrioKorrekt, tvStatPrioZuHoch, tvStatPrioZuNiedrig;

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatKorrekt = (TextView) findViewById(R.id.tvStatKorrekt);
        tvStatPrioKorrekt = (TextView) findViewById(R.id.tvStatPrioKorrekt);
        tvStatPrioZuHoch = (TextView) findViewById(R.id.tvStatPrioZuHoch);
        tvStatPrioZuNiedrig = (TextView) findViewById(R.id.tvStatPrioZuNiedrig);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        sqLiteHelper = new MySQLiteHelper(getApplicationContext());

        aktualisieren();
    }

    @Override
    protected void onResume() {
        super.onResume();
        aktualisieren();
    }

    public void fabPressed(View v) {
        final Intent i = new Intent(this, EingabeMaske0Activity.class);
        startActivity(i);
    }

    public void loescheEintrag(int id) {
        sqLiteHelper.deleteDatensatz(id);
        aktualisieren();
    }

    private void aktualisieren() {
        daten = sqLiteHelper.getAlleDatensaetze();
        myAdapter = new MyAdapter(daten, this);
        recyclerView.setAdapter(myAdapter);

        int[] statCounter = new int[]{0, 0, 0, 0, 0};

        for (Datensatz d : daten) {
            statCounter[d.evaluateStat()]++;
        }

        int totalSize = daten.size();
        if (totalSize > 0) {
            int[] stats = new int[4];
            for (int i = 0; i < stats.length; i++) {
                stats[i] = (100 * statCounter[i]) / totalSize;
            }

            tvStatKorrekt.setText(stats[0] + "%");
            tvStatPrioKorrekt.setText(stats[1] + "%");
            tvStatPrioZuHoch.setText(stats[2] + "%");
            tvStatPrioZuNiedrig.setText(stats[3] + "%");
        }
    }
}
