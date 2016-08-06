package edu.psu.ist402.cleanride;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private EditText firstNameField;
    private EditText lastNameField;
//    private CheckBox isADriver;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    MyDBHandler handler = new MyDBHandler(this);
//    private boolean isDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        usernameField = (EditText) findViewById(R.id.registerUserNameField);
        passwordField = (EditText) findViewById(R.id.registerPasswordField);
        emailField = (EditText) findViewById(R.id.registerEmailField);
        firstNameField = (EditText) findViewById(R.id.registerFirstNameField);
        lastNameField = (EditText) findViewById(R.id.registerLastNameField);
//        isADriver = (CheckBox) findViewById(R.id.registerDriverBox);

        BtnListener listener = new BtnListener();
        ((Button) findViewById(R.id.registerAttemptRegisterButton)).setOnClickListener(listener);
    }
    class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            username = usernameField.getText().toString();
            password = passwordField.getText().toString();
            email = emailField.getText().toString();
            firstName = firstNameField.getText().toString();
            lastName = lastNameField.getText().toString();

            if ((!username.equals("Username") && !username.equals("")) && (!password.equals("Password") && !password.equals(""))
                    && (!email.equals("Email") && !email.equals("")) && (!firstName.equals("First Name") && !firstName.equals(""))
                    && (!lastName.equals("Last Name") && !lastName.equals(""))){
                registerAttemptRegisterButton(view);

                usernameField.setText("Username");
                passwordField.setText("Password");
                emailField.setText("Email");
                firstNameField.setText("First Name");
                lastNameField.setText("Last Name");
//                isADriver.setChecked(false);
            }
            else{
                Snackbar.make(view, "You must enter a value into each field!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    public void registerAttemptRegisterButton (View view){
        if (handler.checkUniqueName(username) == true){
            handler.addUser(username, password, email, firstName, lastName);
            Snackbar.make(view, "You are now a registered user!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            Snackbar.make(view, "That username is already taken. Try again!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void navToLogin(View view){
        Intent goToLogin = new Intent(this, LoginActivity.class);
        startActivity(goToLogin);
        this.finish();
    }
    public void navToHome(View view){
        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);
        this.finish();
    }
}
//I wrote this class - Jinhyuk Kim
