package com.raghulrider.thesmartflow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    public final static String DATA_BASE_NAME = "Insulin_History.db";
    public final static String TABLE_NAME = "insulin_intake_table";
    public final static String COL_1 = "SHOT";
    public final static String COL_2 = "DATE";
    public final static String COL_3 = "TIME";
    public final static String COL_4 = "TYPE";
    public final static String COL_5 = "UNITS";


    public Databasehelper(Context context){
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(SHOT INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TIME TEXT, TYPE TEXT, UNITS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String date, String time, String type, String units){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, date);
        cv.put(COL_3, time);
        cv.put(COL_4, type);
        cv.put(COL_5, units);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) return false;
        else return true;
    }

    public Cursor getALLData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
}
