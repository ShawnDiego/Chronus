package com.example.chronus;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SQDB extends SQLiteOpenHelper {



    SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    public SQDB(Context context) {
        super(context, "SQ.db", null, 1);
    }




    // public SQDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    //     super(context, "SQ.db", null, 1);
    //}

    @Override
    //初始化创建TABLE
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Remind_List( Type CHAR(10), ID String PRIMARY KEY, TITLE String,DAY String ,Content CHAR(20))");
        db.execSQL("CREATE TABLE List( ID String PRIMARY KEY ,name String  ,icon_color String ,number String)");
        // db.execSQL("CREATE TABLE Shopping_List( DAY DATE PRIMARY KEY ,content CHAR(20))");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE Remind_List ADD date 版本已更新 ");
    }


}
