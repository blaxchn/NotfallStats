package com.example.florianbeuckert.notfallstats.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.florianbeuckert.notfallstats.Data.Datensatz;
import com.example.florianbeuckert.notfallstats.Data.Einsatzcode;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String TABLE_STATS = "stats";

    private static final String ID = "id";
    private static final String DATUM = "datum";
    private static final String CODE_GEMELDET = "codeg";
    private static final String CODE_KORREKT = "codek";
    private static final String BEMERKUNG = "bemerkung";
    private static final String KOMMENTAR = "kommentar";

    private static final String[] COLUMNS = {ID, DATUM, CODE_GEMELDET, CODE_KORREKT, BEMERKUNG, KOMMENTAR};

    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "StatDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_db = "CREATE TABLE " + TABLE_STATS +
                " ( " +
                ID + " INTEGER PRIMARY KEY, " +
                DATUM + " TEXT, " +
                CODE_GEMELDET + " TEXT, " +
                CODE_KORREKT + " TEXT, " +
                BEMERKUNG + " TEXT, " +
                KOMMENTAR + " TEXT" +
                " )";
        db.execSQL(create_db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String update_db = "DROP TABLE IF EXISTS " + TABLE_STATS;
        db.execSQL(update_db);
        this.onCreate(db);
    }

    public void addDatensatz(Datensatz d) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues vals = new ContentValues();
        vals.put(ID, d.getId());
        vals.put(DATUM, d.getDatum());
        vals.put(CODE_GEMELDET, d.getCodeGemeldet().toString());
        if (d.getCodeKorrekt() != null)
            vals.put(CODE_KORREKT, d.getCodeKorrekt().toString());
        vals.put(BEMERKUNG, d.getBemerkung());
        vals.put(KOMMENTAR, d.getKommentar());

        db.insert(TABLE_STATS, null, vals);
        db.close();
    }

    public void deleteDatensatz(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, ID + "=" + id, null);
        db.close();
    }

    public Datensatz getDatensatz(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_STATS, COLUMNS,
                " id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                ID + " DESC",
                null);

        if (cursor != null)
            cursor.moveToFirst();

        Datensatz d = new Datensatz();

        d.setId(Integer.parseInt(cursor.getString(0)));
        d.setDatum(cursor.getString(1));
        d.setCodeGemeldet(Datensatz.stringToEinsatzcode(cursor.getString(2)));

        try {
            d.setCodeKorrekt(Datensatz.stringToEinsatzcode(cursor.getString(3)));
        } catch(Exception e) {
            d.setCodeKorrekt(new Einsatzcode());
        }

        d.setBemerkung(cursor.getString(4));
        d.setKommentar(cursor.getString(5));

        return d;
    }

    public List<Datensatz> getAlleDatensaetze() {
        List<Datensatz> ld = new ArrayList<>();
        Datensatz d;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STATS + " ORDER BY " + ID + " DESC";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                d = new Datensatz();

                d.setId(Integer.parseInt(cursor.getString(0)));
                d.setDatum(cursor.getString(1));
                d.setCodeGemeldet(Datensatz.stringToEinsatzcode(cursor.getString(2)));

                try {
                    d.setCodeKorrekt(Datensatz.stringToEinsatzcode(cursor.getString(3)));
                } catch(Exception e) {
                    d.setCodeKorrekt(new Einsatzcode());
                }

                d.setBemerkung(cursor.getString(4));
                d.setKommentar(cursor.getString(5));

                ld.add(d);
            } while (cursor.moveToNext());
        }
        return ld;
    }
}
