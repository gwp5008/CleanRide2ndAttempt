package edu.psu.ist402.cleanride;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    public static final String[] ALL_COLUMNS_USERS = {USER_ID, USERNAME, PASSWORD,
            EMAIL, FIRST_NAME, LAST_NAME};

    public static final String[] ALL_COLUMNS_USERLOCATION = {LOC_USERID, STATE, CITY,
            ACTIVE, DATE_ADDED, DATE_UPDATED, STARTING_POINT, ENDING_POINT, ENDING_DATE, ENDING_TIME, IS_DRIVER};

    public static final String[] ALL_COLUMNS_RESULTSVIEW = { FIRST_NAME, LAST_NAME, EMAIL, IS_DRIVER,
            STATE, CITY, STARTING_POINT, ENDING_POINT, ENDING_DATE, ENDING_TIME};

//    Not sure if ContentProvider code is totally necesary.
    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table " + TABLE_USERS + "(" +
                USER_ID + " integer primary key not null unique autoincrement, " + //might need to remove autoincrement here
                USERNAME + " varchar(20) not null unique, " +
                PASSWORD + " varchar(20) not null, " +
                EMAIL + " varchar(30) not null, " +
                FIRST_NAME + " varchar(20) not null, " +
                LAST_NAME + " varchar(20) not null, ";

        String query2 = "create table " + TABLE_USERLOCATION + "(" +
                LOC_USERID + " integer not null unique, " +
                STATE + " varchar(20) not null, " +
                CITY + " varchar(20) not null, " +
                STARTING_POINT + " varchar(4) not null, " +
                ENDING_POINT + " varchar(4) not null, " +
                ENDING_DATE + " varchar null(20), " +
                ENDING_TIME + " varchar(10) not null, " +
                DATE_ADDED + " text default current_timestamp, " +
                DATE_UPDATED + " text default current_timestamp, " +
                ACTIVE + " varchar(3) not null, " +
                IS_DRIVER + " varchar(3) not null);" +
                "foreign key(" + LOC_USERID + "));";

        String query3 = "create view " + RESULTSVIEW + " as " +
                "select u." + FIRST_NAME + ", u." + LAST_NAME + ", u." + EMAIL + ", ul."
                + STARTING_POINT + ", ul." + STATE + ", ul." + CITY + "ul." + STARTING_POINT + "ul."
                + ENDING_POINT + "ul." + ENDING_DATE + "ul." + ENDING_TIME + "from " + TABLE_USERS
                + " u, " + TABLE_USERLOCATION + " ul where u." + USER_ID + " = ul." + LOC_USERID + ";";
//                + " and ul." + CITY + " = '";+ the_current_users_city + "';"; **This needs added somehow

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERS);
        db.execSQL("drop table if exists " + TABLE_USERLOCATION);
        onCreate(db);
    }
    public void addUser(String username, String password, String email, String fn, String ln){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        values.put(EMAIL, email);
        values.put(FIRST_NAME, fn);
        values.put(LAST_NAME, ln);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public void addUserLocation(String state, String city, String sP, String eP, String eD, String eT,
                                SimpleDateFormat dA, boolean a){
        String date = dA.format(new Date(Calendar.DAY_OF_MONTH));
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATE, state);
        values.put(CITY, city);
        values.put(STARTING_POINT, sP);
        values.put(ENDING_POINT, eP);
        values.put(ENDING_DATE, eD);
        values.put(DATE_ADDED, date);
        values.put(DATE_UPDATED, date);
        values.put(ACTIVE, a);
        values.put(ENDING_TIME, eT);

        db.insert(TABLE_USERLOCATION, null, values);
        db.close();
    }
}