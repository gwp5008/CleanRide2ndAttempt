package edu.psu.ist402.cleanride;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import junit.framework.TestCase;

/**
 * Created by george on 8/3/2016.
 */
public class MyDBHandlerTest extends TestCase {
    RegisterActivity rA;
    MyDBHandler myDB;

    public void testOnCreate() throws Exception {
        rA = new RegisterActivity();
        myDB = new MyDBHandler(rA);
    }
}