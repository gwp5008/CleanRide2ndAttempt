package edu.psu.ist402.cleanride;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class SpecificLocation extends AppCompatActivity {
    private Spinner startingPointLetters;
    private Spinner startingPointNumbers;
    private Spinner endingPointLetters;
    private Spinner endingPointNumbers;
    private ArrayAdapter<CharSequence> sPLettersAdapter;
    private ArrayAdapter<CharSequence> ePLettersAdapter;
    private ArrayAdapter<CharSequence> sPNumbersAdapter;
    private ArrayAdapter<CharSequence> ePNumbersAdapter;
    private Button travelDate;
    private Calendar calendar = Calendar.getInstance();
    private TextView display;
    private int arrivalYear;
    private int arrivalMonth;
    private int arrivalDayOfMonth;
    private int currentYear;
    private int currentMonth;
    private int currentDayOfMonth;
    private MyDBHandler handler = new MyDBHandler(this);

    public static String arrivalDate;
    public static String dateUpdated;
    public static String startingPoint;
    public static String endingPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        travelDate = (Button) findViewById(R.id.calendarBtn);
        display = (TextView) findViewById(R.id.textViewDisplay);

        startingPointLetters = (Spinner) findViewById(R.id.sPLettersSpinner);
        endingPointLetters = (Spinner) findViewById(R.id.ePLettersSpinner);
        startingPointNumbers = (Spinner) findViewById(R.id.sPNumbersSpinner);
        endingPointNumbers = (Spinner) findViewById(R.id.ePNumbersSpinner);

        sPLettersAdapter = ArrayAdapter.createFromResource(this, R.array.letters, android.R.layout.simple_spinner_item);
        sPLettersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingPointLetters.setAdapter(sPLettersAdapter);

        ePLettersAdapter = ArrayAdapter.createFromResource(this, R.array.letters, android.R.layout.simple_spinner_item);
        ePLettersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endingPointLetters.setAdapter(ePLettersAdapter);

        sPNumbersAdapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        sPNumbersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingPointNumbers.setAdapter(sPNumbersAdapter);

        ePNumbersAdapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        ePNumbersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endingPointNumbers.setAdapter(ePNumbersAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void showCalendar(View view){
        new DatePickerDialog(this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            display.setText("Selected date is: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            arrivalYear = year;
            arrivalMonth = monthOfYear;
            arrivalDayOfMonth = dayOfMonth;
            currentYear = calendar.get(Calendar.YEAR);
            currentMonth = calendar.get(Calendar.MONTH);
            currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        }
    };
    public void specficLocNav(View view){
        setInfo();
        if (arrivalDate.equals("")){
            Snackbar.make(view,
                    "You must make all selections; starting point, ending point, and your intended travel date.",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else {
            Intent goToUserDisplay = new Intent(this, UserDisplay.class);
            startActivity(goToUserDisplay);
            this.finish();
        }
    }
    public void setInfo(){
        arrivalDate = arrivalMonth + "/" + arrivalDayOfMonth + "/" + arrivalYear;
        dateUpdated = currentMonth + "/" + currentDayOfMonth + "/" + currentYear;
        startingPoint = startingPointLetters.getSelectedItem().toString() + startingPointNumbers.getSelectedItem().toString();
        endingPoint = endingPointLetters.getSelectedItem().toString() + endingPointNumbers.getSelectedItem().toString();
    }
}
