package com.example.florianbeuckert.notfallstats.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String TABLE_STATS = "stats";

    private static final String ID = "id";
    private static final String DATE = "datum";
    private static final String CODE_REPORTED = "codeg";
    private static final String CODE_ACTUAL = "codek";
    private static final String ANNOTATION = "bemerkung";
    private static final String COMMENT = "kommentar";

    private static final String[] COLUMNS = {ID, DATE, CODE_REPORTED, CODE_ACTUAL, ANNOTATION, COMMENT};

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
                DATE + " TEXT, " +
                CODE_REPORTED + " TEXT, " +
                CODE_ACTUAL + " TEXT, " +
                ANNOTATION + " TEXT, " +
                COMMENT + " TEXT" +
                " )";
        db.execSQL(create_db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String update_db = "DROP TABLE IF EXISTS " + TABLE_STATS;
        db.execSQL(update_db);
        this.onCreate(db);
    }

    public void addDataset(Dataset d) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues vals = new ContentValues();
        vals.put(ID, d.getId());
        vals.put(DATE, d.getDate());
        vals.put(CODE_REPORTED, d.getCodeReported().toString());
        if (d.getCodeActual() != null)
            vals.put(CODE_ACTUAL, d.getCodeActual().toString());
        vals.put(ANNOTATION, d.getAnnotation());
        vals.put(COMMENT, d.getComment());

        db.insert(TABLE_STATS, null, vals);
        db.close();
    }

    public void deleteDataset(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, ID + "=" + id, null);
        db.close();
    }

    public Dataset getDataset(int id) {
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

        Dataset d = new Dataset();

        d.setId(Integer.parseInt(cursor.getString(0)));
        d.setDate(cursor.getString(1));
        d.setCodeReported(Dataset.stringToEmergencyCode(cursor.getString(2)));

        try {
            d.setCodeActual(Dataset.stringToEmergencyCode(cursor.getString(3)));
        } catch(Exception e) {
            d.setCodeActual(new EmergencyCode());
        }

        d.setAnnotation(cursor.getString(4));
        d.setComment(cursor.getString(5));

        return d;
    }

    public List<Dataset> getAllDatasets() {
        List<Dataset> ld = new ArrayList<>();
        Dataset d;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STATS + " ORDER BY " + ID + " DESC";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                d = new Dataset();

                d.setId(Integer.parseInt(cursor.getString(0)));
                d.setDate(cursor.getString(1));
                d.setCodeReported(Dataset.stringToEmergencyCode(cursor.getString(2)));

                try {
                    d.setCodeActual(Dataset.stringToEmergencyCode(cursor.getString(3)));
                } catch(Exception e) {
                    d.setCodeActual(new EmergencyCode());
                }

                d.setAnnotation(cursor.getString(4));
                d.setComment(cursor.getString(5));

                ld.add(d);
            } while (cursor.moveToNext());
        }
        return ld;
    }
}
