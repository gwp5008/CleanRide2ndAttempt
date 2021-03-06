package edu.psu.ist402.cleanride;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by george on 7/2/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    //users table fields
    public static final String DATABASE_NAME = "cleanride.db";
    public static final String TABLE_USERS = "users";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    //user_location table fields
    public static final String TABLE_USERLOCATION = "user_location";
    public static final String LOC_USERID = "user_id";
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String ACTIVE = "active";
    public static final String DATE_ADDED = "date_added";
    public static final String DATE_UPDATED = "date_updated";
    public static final String STARTING_POINT = "starting_point";
    public static final String ENDING_POINT = "ending_point";
    public static final String ENDING_DATE = "ending_date";
    public static final String ENDING_TIME = "ending_time";
    public static final String IS_DRIVER = "is_driver";

    public static final String RESULTSVIEW = "results_view";

//    public static final String[] ALL_COLUMNS_USERS = {USER_ID, USERNAME, PASSWORD,
//            EMAIL, FIRST_NAME, LAST_NAME};
//
//    public static final String[] ALL_COLUMNS_USERLOCATION = {LOC_USERID, STATE, CITY,
//            ACTIVE, DATE_ADDED, DATE_UPDATED, STARTING_POINT, ENDING_POINT, ENDING_DATE, ENDING_TIME, IS_DRIVER};
//
//    public static final String[] ALL_COLUMNS_RESULTSVIEW = { FIRST_NAME, LAST_NAME, EMAIL, IS_DRIVER,
//            STATE, CITY, STARTING_POINT, ENDING_POINT, ENDING_DATE, ENDING_TIME};

//    Not sure if ContentProvider code is totally necesary.
    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table " + TABLE_USERS + "('" +
                USER_ID + "' integer primary key autoincrement not null unique, '" +
                USERNAME + "' varchar(20) not null, '" +
                PASSWORD + "' varchar(20) not null, '" +
                EMAIL + "' varchar(30) not null, '" +
                FIRST_NAME + "' varchar(20) not null, '" +
                LAST_NAME + "' varchar(20) not null);";

        String query2 = "create table " + TABLE_USERLOCATION + "('" +
                LOC_USERID + "' integer not null, '" +
                STATE + "' varchar(20) not null, '" +
                CITY + "' varchar(20) not null, '" +
                STARTING_POINT + "' varchar(4) not null, '" +
                ENDING_POINT + "' varchar(4) not null, '" +
                ENDING_DATE + "' varchar(20) not null, '" +
                DATE_ADDED + "' varchar(20) not null, '" +
                DATE_UPDATED + "' varchar(20), '" +
                ACTIVE + "' varchar(3) not null, '" +
                IS_DRIVER + "' varchar(3) not null, " +
                "foreign key(" + LOC_USERID + ") references " + TABLE_USERS +
                "(" + USER_ID + "));";

        try {
            db.execSQL(query1);
            db.execSQL(query2);
        }
        catch(Exception ex){
//            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + TABLE_USERS);
            db.execSQL("drop table if exists " + TABLE_USERLOCATION);
            onCreate(db);
        }
        catch(Exception ex){
//            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
        }
    }
    public void addUser(View view, String username, String password, String email, String fn, String ln){
//        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        values.put(EMAIL, email);
        values.put(FIRST_NAME, fn);
        values.put(LAST_NAME, ln);

        try{
            this.getWritableDatabase().insert(TABLE_USERS, null, values);
        }
        catch(Exception ex){
//            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
            Snackbar.make(view, "Could not add user.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        finally{
            this.close();
        }
    }

    public void addUserLocation(View view, int userID, String state, String city, String sP, String eP, String eD,
                                String dA, String isDriver){

        ContentValues values = new ContentValues();
        values.put(LOC_USERID, userID);
        values.put(STATE, state);
        values.put(CITY, city);
        values.put(STARTING_POINT, sP);
        values.put(ENDING_POINT, eP);
        values.put(ENDING_DATE, eD);
        values.put(DATE_ADDED, dA);
        values.put(DATE_UPDATED, dA);
        values.put(ACTIVE, "active");
        values.put(IS_DRIVER, isDriver);

        try{
            this.getWritableDatabase().insertOrThrow(TABLE_USERLOCATION, null, values);
        }
        catch(Exception ex){
//            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
            Snackbar.make(view, "Could not add your location information.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        finally{
            this.close();
        }
    }
    public boolean checkUniqueName(String username){
        boolean isUsable;
        int placeholder = 0;
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from users where username = '" + username + "';", null);

        while (cursor.moveToNext()){
            placeholder++;
        }
        if (placeholder >= 1){
            isUsable = false;
        }
        else{
            isUsable = true;
        }
        cursor.close();
        return isUsable;
    }
    public void checkLogin(String username, String password){
        int placeholder = 0;
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from users where username = '" + username +
                "' and password = '" + password + "';", null);

        while (cursor.moveToNext()) {
            placeholder++;
        }
        if (placeholder == 1){
            MainActivity.isLoggedIn = true;
        }
        else{
            MainActivity.isLoggedIn = false;
        }
        cursor.close();
    }
    public void createView(View view, String city){
        String query3 = "create view " + RESULTSVIEW + " as "
                + "select u." + FIRST_NAME + ", u." + LAST_NAME + ", u." + EMAIL
                + ", ul." + STATE + ", ul." + CITY + ", ul." + STARTING_POINT + ", ul."
                + ENDING_POINT + ", ul." + ENDING_DATE + ", ul." + IS_DRIVER + " from " + TABLE_USERS
                + " u, " + TABLE_USERLOCATION + " ul where u." + USER_ID + " = ul." + LOC_USERID
                + " and ul." + CITY + " = '" + city + "';";

        try{
            this.getReadableDatabase().execSQL(query3);
        }
        catch(Exception ex) {
//            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
            Snackbar.make(view, "Could not assemble your city's information.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public Cursor getView(){
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from results_view;", null);
        return cursor;
    }
    public int getID(String username, String password){
        int userID = 0;
        Cursor cursor = this.getReadableDatabase().rawQuery("select user_id from users where username = '" +
                username + "' and password = '" + password + "';", null);

        while (cursor.moveToNext()){
            userID = cursor.getInt(0);
        }
        cursor.close();
        return userID;
    }
    public void dropTables(View view){
        String query4 = "drop table users;";
        String query5 = "drop table user_location;";

        try{
            this.getWritableDatabase().execSQL(query4);
            this.getWritableDatabase().execSQL(query5);
        }
        catch(Exception ex){
            Snackbar.make(view, "Could not delete tables.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        finally {
            this.close();
        }
    }
}
//I wrote this class - George Pendleton