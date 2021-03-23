package com.example.datedifferencedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Calendar calFrom, calTo;
    private TextView textView;
    private DatePicker dpFrom, dpTo;
    private ImageButton b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calFrom = Calendar.getInstance();
        calTo = Calendar.getInstance();
        dpFrom = (DatePicker) findViewById(R.id.firstDatePicker);
        dpTo = (DatePicker) findViewById(R.id.secondDatePicker);
        textView = (TextView) findViewById(R.id.textView);
        b = (ImageButton) findViewById(R.id.calculateTheDifferenceButton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    private void updateCalendarFromDatePicker(Calendar cal, DatePicker dp) {
        calFrom.set(dpFrom.getYear(), dpFrom.getMonth(), dpFrom.getDayOfMonth());
        calTo.set(dpTo.getYear(), dpTo.getMonth(), dpTo.getDayOfMonth());
    }

    private void calculate() {
        updateCalendarFromDatePicker(calFrom, dpFrom);
        updateCalendarFromDatePicker(calTo, dpTo);

        if (calTo.compareTo(calFrom) < 0) {
            Calendar temp = calTo;
            calTo = calFrom;
            calFrom = temp;

            int days = 0;
            while (calTo.compareTo(calFrom) != 0) {
                calFrom.add(Calendar.DAY_OF_YEAR, 1);
                days++;
            }
            calFrom.add(Calendar.DAY_OF_YEAR, 1);
            textView.setText(String.valueOf(days) + " days");
        } else if (calTo.compareTo(calFrom) > 0) {
            int days = 0;
            while (calTo.compareTo(calFrom) != 0) {
                calFrom.add(Calendar.DAY_OF_YEAR, 1);
                days++;
            }
            calFrom.add(Calendar.DAY_OF_YEAR, 1);
            textView.setText(String.valueOf(days) + " days");
        } else {
            textView.setText("0 days");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("calendar 1", calFrom);
        outState.putSerializable("calendar 2", calTo);
        outState.putString("text", (String) textView.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        calFrom = (Calendar) savedInstanceState.getSerializable("calendar 1");
        calTo = (Calendar) savedInstanceState.getSerializable("calendar 2");
        textView.setText(savedInstanceState.getString("text"));
    }
}