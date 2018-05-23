package com.example.florianbeuckert.notfallstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper sqLiteHelper;
    private List<Datensatz> daten = new ArrayList<Datensatz>();

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new MySQLiteHelper(this);
        //sqLiteHelper.addDatensatz(new Datensatz(361, new Date(), new Einsatzcode(20, 11, false), new Einsatzcode(10, 10, true), "abcdefgh", "kein kommentar"));
        daten = sqLiteHelper.getAlleDatensaetze();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        myAdapter = new MyAdapter(daten);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(myAdapter);
    }

    public void fabPressed(View v) {
        final Intent i = new Intent(this, EingabeMaske0Activity.class);
        startActivity(i);
    }
}
