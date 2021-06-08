package com.example.Project_1171503_1171214;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {


    public DataBaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context) {
        super(context, "ProjectAndroid2021", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Person(Email TEXT PRIMARY KEY,First_Name TEXT,Last_Name TEXT, Phone TEXT,PASSWORD TEXT ,Gender TEXT,City TEXT,Country TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE Cars(id INTEGER PRIMARY KEY NOT NULL,Year TEXT,Make TEXT,Model TEXT,Distance TEXT,Price TEXT,Accidents TEXT,Offers TEXT,FAVSTATUS TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITE(EMAIL TEXT, CARID INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE RESERVE(EMAIL TEXT, CARID INTEGER, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /********************************PERSON********************************/
    public void insertPerson(Person person) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", person.getFirst_name());
        contentValues.put("Last_Name", person.getSecond_name());
        contentValues.put("Phone", person.getPhone());
        contentValues.put("Email", person.getEmail());
        contentValues.put("PASSWORD", person.getPassword());
        contentValues.put("Gender", person.getGender());
        contentValues.put("City", person.getCity());
        contentValues.put("Country", person.getCountry());
        sqLiteDatabase.insert("Person", null, contentValues);
    }


    public Cursor getAllPerson() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Person", null);
    }

    public int searchBy_email_pass(String Email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String temp = "SELECT * FROM Person WHERE Email = " + '"' + Email + '"' + " AND Password = " + '"' + password + '"';
        System.out.println(temp);
        return sqLiteDatabase.rawQuery(temp, null).getCount();

    }

    public int searchBy_email(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String temp = "SELECT * FROM Person WHERE Email = " + '"' + Email + '"';
        System.out.println(temp);
        return sqLiteDatabase.rawQuery(temp, null).getCount();

    }


    public Cursor searchBy_email_(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String temp = "SELECT * FROM Person WHERE Email = " + '"' + Email + '"';
        return sqLiteDatabase.rawQuery("SELECT * FROM Person", null);





    }

    public void update_person(String email , String first_name , String last_name , String phone , String password ){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String temp = "UPDATE Person SET First_Name = " + '"' + first_name + '"' + ", Last_Name = " +'"' + last_name + '"' + ", Phone = " + '"' + phone +'"' + ", PASSWORD = " + '"' + password + '"' + " WHERE Email = " + '"' +  email +'"' + ";";
        sqLiteDatabase.execSQL(temp);


    }

    /********************************CAR********************************/

    public void insertCar(String make, String model, String year, String distance, String accidents, String fav_status, String price, String offers) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Make", make);
        contentValues.put("Model", model);
        contentValues.put("Year", year);
        contentValues.put("Distance", distance);
        contentValues.put("Accidents", accidents);
        contentValues.put("FAVSTATUS", fav_status);
        contentValues.put("Offers", offers);
        contentValues.put("Price", price);
        sqLiteDatabase.insert("Cars", null, contentValues);
    }

    public Cursor getAllCars() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Cars", null);
    }

    public Cursor getCarGivenId(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String temp = "SELECT * FROM Cars WHERE id = " + '"' + id + '"';
        return sqLiteDatabase.rawQuery(temp, null);
    }

    /********************************FAVORITE********************************/

    public void insertFavorite(String email, int carid) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("CARID", carid);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("FAVORITE", null, contentValues);
    }


    public Cursor getAllFavorite() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM FAVORITE", null);
        return cursor;
    }

    public Cursor getFavByEmail(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM FAVORITE WHERE EMAIL = '" + email + "'", null);
        return cursor;
    }

    /********************************RESERVE********************************/

    public void insertReserve(String email, int carid, String date) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("CARID", carid);
        contentValues.put("DATE", date);
        sqLiteDatabase.insert("RESERVE", null, contentValues);
    }


    public Cursor getAllReserves() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RESERVE", null);
        return cursor;
    }

    public Cursor getReservesByEmail(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RESERVE WHERE EMAIL = '" + email + "'", null);
        return cursor;
    }

}
