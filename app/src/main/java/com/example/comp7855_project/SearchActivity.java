package com.example.comp7855_project;

import android.app.DatePickerDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private EditText fromDate_Year;
    private EditText fromDate_Month;
    private EditText fromDate_Day;
    private EditText toDate_Year;
    private EditText toDate_Month;
    private EditText toDate_Day;
    private EditText Tag;
    private EditText location_X;
    private EditText location_Y;
    private EditText location_Radius;
    private Calendar fromCalendar;
    private Calendar toCalendar;
    private DatePickerDialog.OnDateSetListener fromListener;
    private DatePickerDialog.OnDateSetListener toListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fromDate_Year = findViewById(R.id.search_fromYear);
        fromDate_Month = findViewById(R.id.search_fromMonth);
        fromDate_Day = findViewById(R.id.search_fromDay);
        toDate_Year   = findViewById(R.id.search_toYear);
        toDate_Month   = findViewById(R.id.search_toMonth);
        toDate_Day   = findViewById(R.id.search_toDay);
        Tag = findViewById(R.id.search_Tag);
        location_X = findViewById(R.id.search_LocationX);
        location_Y = findViewById(R.id.search_LocationY);
        location_Radius = findViewById(R.id.search_Radius);

        Button Btn_search = findViewById(R.id.search_cancel);
        Button Btn_cancel = findViewById(R.id.search_search);

        Btn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        Btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent();
                if (!fromDate_Year.getText().toString().isEmpty() && !fromDate_Month.getText().toString().isEmpty() && !fromDate_Day.getText().toString().isEmpty() && !toDate_Year.getText().toString().isEmpty() && !toDate_Month.getText().toString().isEmpty() && !toDate_Day.getText().toString().isEmpty()) {
                    i.putExtra("STARTDATE", String.format("%04d", Integer.valueOf(fromDate_Year.getText().toString())) + String.format("%02d", Integer.valueOf(fromDate_Month.getText().toString())) + String.format("%02d", Integer.valueOf(fromDate_Day.getText().toString())));
                    i.putExtra("ENDDATE", String.format("%04d", Integer.valueOf(toDate_Year.getText().toString())) + String.format("%02d", Integer.valueOf(toDate_Month.getText().toString())) + String.format("%02d", Integer.valueOf(toDate_Day.getText().toString())));
                    i.putExtra("TAG", Tag.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
                }
                else {
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.activity_search), "Invalid Parameter", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                }

            }
        });
    }

    public void cancel(final View v) {
        finish();
    }

    public void search(final View v) {
        Intent i = new Intent();
        if (fromDate_Year.getText().toString() != null && fromDate_Month.getText().toString() != null && fromDate_Day.getText().toString() != null && toDate_Year.getText().toString() != null && toDate_Month.getText().toString() != null && toDate_Day.getText().toString() != null) {
            i.putExtra("STARTDATE", String.format("%04d", Integer.valueOf(fromDate_Year.getText().toString())) + String.format("%02d", Integer.valueOf(fromDate_Month.getText().toString())) + String.format("%02d", Integer.valueOf(fromDate_Day.getText().toString())));
            i.putExtra("ENDDATE", String.format("%04d", Integer.valueOf(toDate_Year.getText().toString())) + String.format("%02d", Integer.valueOf(toDate_Month.getText().toString())) + String.format("%02d", Integer.valueOf(toDate_Day.getText().toString())));
            setResult(RESULT_OK, i);
            finish();
        }
        else {
            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.activity_main), "Invalid Parameter", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }


    }
}

