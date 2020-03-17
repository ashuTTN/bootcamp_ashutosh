package com.example.alarmmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reminder Set!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MyAlarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();
                long intervalInMillis = 6000;
                //alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick+tenSecondsInMillis,pendingIntent);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeAtButtonClick, intervalInMillis, pendingIntent);  //If intervalInMillis is less than 60000 ms in  android < 5.1 it will force it to 60000 ms
                /*
                 ELAPSED_REALTIME—Fires the pending intent based on the amount of time since the device was booted, but doesn't wake up the device. The elapsed time includes any time during which the device was asleep.
                ELAPSED_REALTIME_WAKEUP—Wakes up the device and fires the pending intent after the specified length of time has elapsed since device boot.
                RTC—Fires the pending intent at the specified time but does not wake up the device.
                RTC_WAKEUP—Wakes up the device to fire the pending intent at the specified time.
                 */
            }
        });
    }
}
