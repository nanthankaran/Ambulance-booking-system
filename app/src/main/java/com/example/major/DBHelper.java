package com.example.major;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        super(context, "Amb.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Amb(name TEXT primary key, place TEXT,mobile TEXT,hospital TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Amb");
    }

    public Boolean insertAmbdata(String name , String place, String mobile, String hospital )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("place", place);
        contentValues.put("mobile", mobile);
        contentValues.put("hospital",hospital);


        long result=DB.insert("Amb",null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Amb",null);
        return cursor;
    }

}
