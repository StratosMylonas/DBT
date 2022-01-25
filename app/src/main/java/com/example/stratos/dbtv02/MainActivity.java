package com.example.stratos.dbtv02;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        LinearLayout newMeasurementButton = findViewById(R.id.newMeasurementButton);
        LinearLayout calendarButton = findViewById(R.id.calendarButton);
        LinearLayout measurementsListButton = findViewById(R.id.measurementsListButton);
        LinearLayout medBookButton = findViewById(R.id.medBookButton);
        LinearLayout statisticsButton = findViewById(R.id.statisticsButton);
        LinearLayout reminderButton = findViewById(R.id.reminderButton);

        newMeasurementButton.setOnClickListener(v -> {
            newMeasurementButton.startAnimation(animation);

            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(MainActivity.this, NewMeasurement.class);
                startActivity(i1);
            }, 500);
        });

        calendarButton.setOnClickListener(v -> {
            calendarButton.startAnimation(animation);

            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(MainActivity.this, Calendar.class);
                startActivity(i1);
            }, 400);
        });

        measurementsListButton.setOnClickListener(v -> {
            measurementsListButton.startAnimation(animation);

            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(MainActivity.this, Measurement_List.class);
                startActivity(i1);
            }, 400);
        });

        medBookButton.setOnClickListener(v -> {
            medBookButton.startAnimation(animation);

            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(MainActivity.this, MedBook.class);
                startActivity(i1);
            }, 400);
        });

        statisticsButton.setOnClickListener(v -> {
            statisticsButton.startAnimation(animation);

            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(MainActivity.this, Stats.class);
                startActivity(i1);
            }, 400);
        });

        reminderButton.setOnClickListener(v -> {
            reminderButton.startAnimation(animation);

            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(MainActivity.this, Reminder.class);
                startActivity(i1);
            }, 400);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
