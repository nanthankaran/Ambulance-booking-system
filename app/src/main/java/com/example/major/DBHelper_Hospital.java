package com.example.major;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_Hospital extends SQLiteOpenHelper {
    public DBHelper_Hospital(Context context ) {
        super(context, "Hospital_Register.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table hospital_reg(email Text primary key,password Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {

        myDB.execSQL("drop Table if exists hospital_reg");
    }
    public Boolean insertData(String email,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result =myDB.insert("  hospital_reg",null,contentValues);

        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean checkemail(String email)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from hospital_reg where email = ?",new String[]{email});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkemailPassword(String email,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from hospital_reg where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0){
            return true;

        }
        else {
            return false;
        }
    }


    public Boolean updatepassword(String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password);
        long result =myDB.update("hospital_reg",contentValues,"email = ?",new String[] {email});
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
}
