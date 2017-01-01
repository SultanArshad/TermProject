package fasssoft.eventhallfinder.models.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fasssoft.eventhallfinder.models.User;

/**
 * Created by sultan on 1/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_HALLNAME = "hallname";
    private static final String COLUMN_LOCATION = "location";
    //    private static final String COLUMN_CREATED_AT = "created_at";
//    private static final String COLUMN_UPDATED_AT = "updated_at";
//    private static final String COLUMN_DELETED_AT = "deleted_at";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table users (id integer primary key not null , " +
            "name text not null , email text not null , password text not null , hallname text not null , location text not null);";
    // , created_at text not null , updated_at text not null , deleted_at text not null

//    "id": 1,
//            "name": "1sultan arshad",
//            "email": "1sultan@gmail.com",
//            "password": "1sultan123",
//            "hallname": "1silk event",
//            "location": "1khachakhooo",
//            "created_at": "2016-12-27 20:03:39",
//            "updated_at": "2016-12-27 20:07:16",
//            "deleted_at": null


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qurey = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(qurey);
        this.onCreate(db);
    }

    public String searchPass(String email) {
        db = this.getReadableDatabase();
        String queery = "select email,password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(queery, null);
        String aEmail, bPass="not found";
        if (cursor.moveToFirst()) {
            do {
                aEmail = cursor.getString(0);
                bPass = cursor.getString(1);
                if (aEmail.equals(email)) {
                    bPass = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return bPass;
    }

    public void insertUser(User u) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_PASSWORD, u.getPassword());
        values.put(COLUMN_HALLNAME, u.getHallname());
        values.put(COLUMN_LOCATION, u.getLocation());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

}
