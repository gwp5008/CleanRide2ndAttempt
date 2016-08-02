package edu.psu.ist402.cleanride;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UserDisplay extends AppCompatActivity {
    private MyDBHandler handler = new MyDBHandler(this);
    private Button showUsersBtn;
    private Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showUsersBtn = (Button) findViewById(R.id.showUserBtn);
        goHome = (Button) findViewById(R.id.uDReturnHome);

        ImageView imgView = (ImageView) findViewById(R.id.cityMapUDisplay);
        Drawable drawable = GeneralLocation.mapChoice;
        imgView.setImageDrawable(drawable);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void showUsers(View view) {
        Cursor allData = handler.getView();
        StringBuffer buffer = new StringBuffer();

        while (allData.moveToNext()) {
            buffer.append("First Name: " + allData.getString(0) + "\n");
            buffer.append("Last Name: " + allData.getString(1) + "\n");
            buffer.append("Email: " + allData.getString(2) + "\n");
            buffer.append("State: " + allData.getString(3) + "\n");
            buffer.append("City: " + allData.getString(4) + "\n");
            buffer.append("Desired Starting Point: " + allData.getString(5) + "\n");
            buffer.append("Desired End Point: " + allData.getString(6) + "\n");
            buffer.append("Desired End Date: " + allData.getString(7) + "\n");
            buffer.append("Is A Driver?: " + allData.getString(8) + "\n\n");
        }
        showMessage("Nearby Users", buffer.toString());
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void navToHome(View view){
        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);
        this.finish();
    }
}