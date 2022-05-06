package com.mohammedtawhied122.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "patientDB";
    public static final int DB_VERSION = 1;
    public static final String TB_NAME = "patientLogIn";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TB_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT UNIQUE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TB_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Patient> selectByPassword(String password) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TB_NAME + " WHERE password = ?", new String[]{password});

        if(cursor != null && cursor.moveToFirst()) {
            do {
                int idFromDB = cursor.getInt(0);
                String usernameFromDB = cursor.getString(1);
                String passwordFromDB = cursor.getString(2);

                Patient patient = new Patient(String.valueOf(idFromDB), usernameFromDB, passwordFromDB);
                patientArrayList.add(patient);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return patientArrayList;
    }
}
