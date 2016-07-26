package edu.psu.ist402.cleanride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    MyDBHandler handler = new MyDBHandler(this);
    private EditText usernameField; private EditText passwordField;
    private String username; private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameField = (EditText) findViewById(R.id.loginUserNameField);
        passwordField = (EditText) findViewById(R.id.loginPasswordField);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        BtnListener listener = new BtnListener();
        ((Button) findViewById(R.id.loginButton)).setOnClickListener(listener);
    }
    class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            username = usernameField.getText().toString();
            password = passwordField.getText().toString();

            attemptLogin(view);

            usernameField.setText("Username");
            passwordField.setText("");
        }
    }
    public void navToHome(View view){
        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);
        this.finish();
    }
    public void navToRegister(View view){
        Intent goToRegister = new Intent(this, RegisterActivity.class);
        startActivity(goToRegister);
        this.finish();
    }
    public void attemptLogin(View view){
        handler.checkLogin(username, password);

        if (MainActivity.isLoggedIn == true){
            Snackbar.make(view, "Congradulations! You are now logged in.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            Snackbar.make(view, "You information does not match. You are not logged in.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
