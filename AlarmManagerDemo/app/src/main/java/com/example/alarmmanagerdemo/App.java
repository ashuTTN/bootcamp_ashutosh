package com.example.alarmmanagerdemo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class App extends Application {
    public static final String CHANNEL_1_ID = "CHANNEL 1";
    public static final String CHANNEL_2_ID = "CHANNEL 2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }
    public void createNotificationChannels(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("What this channel1 is for ");
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,
                    "channel 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("what this channel 2 is for");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }
}
