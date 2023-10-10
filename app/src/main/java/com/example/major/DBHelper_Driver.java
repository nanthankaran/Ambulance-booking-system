package com.example.major;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_Driver extends SQLiteOpenHelper {
    public DBHelper_Driver(Context context ) {

        super(context, "Driver_Register.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table driver_reg(email Text primary key,password Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {

        myDB.execSQL("drop Table if exists driver_reg");
    }
    public Boolean insertData(String email,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result =myDB.insert("driver_reg",null,contentValues);

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
        Cursor cursor = myDB.rawQuery("select * from driver_reg where email = ?",new String[]{email});
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
        Cursor cursor = myDB.rawQuery("select * from driver_reg where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0){
            return true;

        }
        else {
            return false;
        }
    }


    public Boolean updatepassword(String email,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password);
        long result =myDB.update("driver_reg",contentValues,"email = ?",new String[] {email});
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }


}
