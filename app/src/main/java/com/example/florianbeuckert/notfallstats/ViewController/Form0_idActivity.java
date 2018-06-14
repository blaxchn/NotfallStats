package com.example.florianbeuckert.notfallstats.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florianbeuckert.notfallstats.Data.MySQLiteHelper;
import com.example.florianbeuckert.notfallstats.R;

public class Form0_idActivity extends AppCompatActivity {

    private EditText editTextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form0);

        getSupportActionBar().setTitle(R.string.header_form);

        editTextID = (EditText) findViewById(R.id.editTextID);
    }

    public void next0Pressed(View v) {
        String id_string = editTextID.getText().toString();
        int id = -1;

        try {
            id = Integer.parseInt(id_string);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.info_text_id_not_valid), Toast.LENGTH_SHORT).show();
            return;
        }

        if (id == -1) {
            Toast.makeText(this, getString(R.string.info_text_id_not_valid), Toast.LENGTH_SHORT).show();
            return;
        }

        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(getApplicationContext());
        if (sqLiteHelper.datasetExists(id)) {
            Toast.makeText(this, getString(R.string.info_text_id_exists), Toast.LENGTH_SHORT).show();
            return;
        }

        final Intent i = new Intent(this, Form1_codeReportedActivity.class);

        i.putExtra("id", id);

        startActivity(i);
    }
}
