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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private EditText fromDate;
    private EditText toDate;
    private Calendar fromCalendar;
    private Calendar toCalendar;
    private DatePickerDialog.OnDateSetListener fromListener;
    private DatePickerDialog.OnDateSetListener toListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fromDate = findViewById(R.id.search_fromDate);
        toDate   = findViewById(R.id.search_toDate);
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
                i.putExtra("STARTDATE", fromDate.getText().toString());
                i.putExtra("ENDDATE", toDate.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }



    public void cancel(final View v) {
        finish();
    }

    public void search(final View v) {
        Intent i = new Intent();
        i.putExtra("STARTDATE", fromDate.getText().toString());
        i.putExtra("ENDDATE", toDate.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }
}

