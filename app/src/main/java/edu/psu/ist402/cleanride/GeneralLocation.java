package edu.psu.ist402.cleanride;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class GeneralLocation extends AppCompatActivity {
    private Spinner stateSpinner;
    private Spinner citySpinner;
    private CheckBox isDriverBox;
    private ArrayAdapter<CharSequence> stateAdapter;
    private ArrayAdapter<CharSequence> cityAdapter;
    public static String state;
    public static String city;
    public static String isDriver;
    public static Drawable mapChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        isDriverBox = (CheckBox) findViewById(R.id.driverBox);

        stateAdapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).equals("")) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).equals("")) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void onClickGeneralMap(View view){
        if (stateSpinner.getSelectedItem().toString().equals("") || citySpinner.getSelectedItem().toString().equals("")){
            Snackbar.make(view, "You must enter a city and state first!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            FrameLayout map = (FrameLayout) findViewById(R.id.gridFrameMap);
            setMapImage(view);
            map.setVisibility(View.VISIBLE);
        }
    }
    public void onClickGeneralNav(View view) {
        state = stateSpinner.getSelectedItem().toString();
        city = citySpinner.getSelectedItem().toString();
        if (isDriverBox.isChecked()){
            isDriver = "yes";
        }
        else {
            isDriver = "no";
        }
//        Snackbar.make(view, "The value of isDriver is " + isDriver, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
        Intent goToSpecificA = new Intent(this, SpecificLocation.class);
        startActivity(goToSpecificA);
        this.finish();
    }
    public void setMapImage(View view){
        //    More code could go in this method in the event that we wanted to add more maps.
        switch(stateSpinner.getSelectedItem().toString()){
            case("Pennsylvania"):
                switch(citySpinner.getSelectedItem().toString()){
                    case("State College"):
                        ImageView imgView=(ImageView) findViewById(R.id.cityMapGeneral);
                        Drawable drawable  = getResources().getDrawable(R.drawable.sc_map);
                        imgView.setImageDrawable(drawable);
                        mapChoice = getResources().getDrawable(R.drawable.sc_map);
                        break;
                }
        }
    }
}
