package com.example.stratos.dbtv02;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Measurements.db";
    public static final String TABLE_NAME = "Data";
    public static final String TABLE_NAME2 = "Notifications";
    public static final String TABLE_NAME3 = "Patient";
    public static final String TABLE_NAME4 = "Doctors";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATEOFMEASURE";
    public static final String COL_3 = "VALUE";
    public static final String COL_4 = "TYPE";
    public static final String COL_5 = "DAY";

    public static final String COL_2_1 = "TIME";
    public static final String COL_2_2 = "ON_OFF";

    public static final String COL_3_1 = "NAME";
    public static final String COL_3_2 = "SURNAME";
    public static final String COL_3_3 = "ADDRESS";
    public static final String COL_3_4 = "HOMETOWN";
    public static final String COL_3_5 = "TK";
    public static final String COL_3_6 = "EMAIL";
    public static final String COL_3_7 = "PHONE";
    public static final String COL_3_8 = "CELLPHONE";

    public static final String COL_4_1 = "NAME";
    public static final String COL_4_2 = "SURNAME";
    public static final String COL_4_3 = "ADDRESS";
    public static final String COL_4_4 = "EMAIL";
    public static final String COL_4_5 = "PHONE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 14);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATEOFMEASURE DATETIME,VALUE INT, TYPE TEXT, DAY TEXT)");
        db.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TIME TEXT,ON_OFF INT)");
        db.execSQL("create table " + TABLE_NAME3 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, SURNAME TEXT, ADDRESS TEXT, HOMETOWN TEXT, TK TEXT, EMAIL TEXT, PHONE TEXT, CELLPHONE TEXT)");
        db.execSQL("create table " + TABLE_NAME4 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, SURNAME TEXT,ADDRESS TEXT, EMAIL TEXT, PHONE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    public boolean insertData(int value, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        contentValues.put(COL_2, dateFormat.format(date));
        contentValues.put(COL_3, value);
        contentValues.put(COL_4, type);
        contentValues.put(COL_5, Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }


    public void insertPersonalData(String name, String surname, String address, String hometown, String tk, String email, String phone, String cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3_1, name);
        contentValues.put(COL_3_2, surname);
        contentValues.put(COL_3_3, address);
        contentValues.put(COL_3_4, hometown);
        contentValues.put(COL_3_5, tk);
        contentValues.put(COL_3_6, email);
        contentValues.put(COL_3_7, phone);
        contentValues.put(COL_3_8, cell);

        long result = db.insert(TABLE_NAME3, null, contentValues);
    }


    public void insertTime(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_1, time);
        contentValues.put(COL_2_2, 0);
        long result = db.insert(TABLE_NAME2, null, contentValues);
    }

    public void insertDoctorsData(String name, String surname, String address, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_4_1, name);
        contentValues.put(COL_4_2, surname);
        contentValues.put(COL_4_3, address);
        contentValues.put(COL_4_4, email);
        contentValues.put(COL_4_5, phone);

        long result = db.insert(TABLE_NAME4, null, contentValues);
    }


    public Cursor getAllDoctorsData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME4, null);
    }

    public void updateDoctorsData(String name, String surname, String address, String email, String phone, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME4 + " SET " + COL_4_1 + " = '" + name + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME4 + " SET " + COL_4_2 + " = '" + surname + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME4 + " SET " + COL_4_3 + " = '" + address + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME4 + " SET " + COL_4_4 + " = '" + email + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME4 + " SET " + COL_4_5 + " = '" + phone + "' WHERE ID = " + id);
    }


    public Cursor getAllPersonalData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME3, null);
    }

    public void updatePersonalData(String name, String surname, String address, String hometown, String tk, String email, String phone, String cell, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_1 + " = '" + name + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_2 + " = '" + surname + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_3 + " = '" + address + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_4 + " = '" + hometown + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_5 + " = '" + tk + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_6 + " = '" + email + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_7 + " = '" + phone + "' WHERE ID = " + id);
        db.execSQL("UPDATE " + TABLE_NAME3 + " SET " + COL_3_8 + " = '" + cell + "' WHERE ID = " + id);
    }


    public void updateState(int state, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME2 + " SET " + COL_2_2 + " = " + state + " WHERE ID = " + id);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }


    public Cursor getAllTimes() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME2, null);
    }

    public Cursor get24hData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME + " where " + COL_2 + " >= DATE('now', '-1 days')", null);
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }


    public void deleteTime(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, "ID = ?", new String[]{id});
    }
}
