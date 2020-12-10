package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.remindme.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private int notificationId = 1;

@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_main);

        //Set OnClick Listener.

        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);

        }

@Override
public  void onClick(View view) {
    EditText editText = findViewById(R.id.editText);
    TimePicker timePicker =  findViewById(R.id.timePicker);

    //set notificationId & text.

    Intent intent  =   new Intent(MainActivity.this,AlarmManager.class);
    intent.putExtra("notificationId" , notificationId);
    intent.putExtra("todo",  editText.getText(). toString());


    PendingIntent alaramIntent = PendingIntent.getBroadcast(MainActivity.this , 0 ,
            intent ,  PendingIntent.FLAG_CANCEL_CURRENT);

    AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

    switch (view.getId())
{

        case R.id.setBtn:
            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();

            //Create time.
            Calendar startTime =  Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY , hour);
            startTime.set(Calendar.MINUTE , minute);
            startTime.set(Calendar.SECOND ,  0);
            long alarmStartTime =  startTime.getTimeInMillis();


            alarm.set(AlarmManager.RTC_WAKEUP ,  alarmStartTime ,  alaramIntent);

            Toast.makeText(this, "Done ! " , Toast.LENGTH_SHORT).show();

            break;

        case R.id.cancelBtn:
            alarm.cancel(alaramIntent);
            Toast.makeText(this, "canceled. " , Toast.LENGTH_SHORT).show();
            break;





    }




     }

}
