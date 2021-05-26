package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
//timePicker , setAlaram

public class MainActivity extends AppCompatActivity {

    Button setAlaram;
    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAlaram = findViewById(R.id.setAlaram);
        timePicker = findViewById(R.id.timePicker);

        setAlaram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 //Since we need to provide the call Picker values in milliseconds we will use the calendar for that
                Calendar calendar = Calendar.getInstance();

                calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONDAY),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.getHour(),
                        timePicker.getHour(),0
                );

                ringAlaram(calendar.getTimeInMillis());
            }
        });
    }

    private void ringAlaram(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this,myAlaram.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,123,i,0);

        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,AlarmManager.INTERVAL_DAY,pi);
        Toast.makeText(this,"Alaram is Set",Toast.LENGTH_SHORT).show();
    }
}