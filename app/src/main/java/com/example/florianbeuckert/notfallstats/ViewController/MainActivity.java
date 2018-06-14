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

import com.example.florianbeuckert.notfallstats.Data.Dataset;
import com.example.florianbeuckert.notfallstats.Data.MySQLiteHelper;
import com.example.florianbeuckert.notfallstats.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper sqLiteHelper;
    private List<Dataset> data = new ArrayList<Dataset>();

    private TextView tvStatCorrekt, tvStatPriorityCorrect, tvStatPriorityTooHigh, tvStatPriorityTooLow;

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatCorrekt = (TextView) findViewById(R.id.tvStatCorrekt);
        tvStatPriorityCorrect = (TextView) findViewById(R.id.tvStatPriorityCorrect);
        tvStatPriorityTooHigh = (TextView) findViewById(R.id.tvStatPriorityTooHigh);
        tvStatPriorityTooLow = (TextView) findViewById(R.id.tvStatPriorityTooLow);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        sqLiteHelper = new MySQLiteHelper(getApplicationContext());

        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void fabPressed(View v) {
        final Intent i = new Intent(this, Form0_idActivity.class);
        startActivity(i);
    }

    public void deleteDataset(int id) {
        sqLiteHelper.deleteDataset(id);
        refresh();
    }

    private void refresh() {
        data = sqLiteHelper.getAllDatasets();
        myAdapter = new MyAdapter(data, this);
        recyclerView.setAdapter(myAdapter);

        int[] stats = Dataset.getStatArray(data);
        tvStatCorrekt.setText(stats[0] + "%");
        tvStatPriorityCorrect.setText(stats[1] + "%");
        tvStatPriorityTooHigh.setText(stats[2] + "%");
        tvStatPriorityTooLow.setText(stats[3] + "%");
    }
}
