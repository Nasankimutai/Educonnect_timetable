package com.example.educonnectapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(First_name text, Last_name text, Email text, password text)";
        db.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void create_account(String First_name, String Last_name, String Email, String password){
        ContentValues cv = new ContentValues();
        cv.put("First_name",First_name);
        cv.put("Last_name",Last_name);
        cv.put("Email",Email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String Email, String password){
        int result=0;
        String[] str = new String[2];
        str[0] = Email;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where Email=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;

    }
    public Cursor getUserFirstName(String email) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT First_name FROM users WHERE Email=?", new String[]{email});
    }
}
