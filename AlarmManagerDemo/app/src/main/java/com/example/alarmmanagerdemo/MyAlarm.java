package com.example.alarmmanagerdemo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.alarmmanagerdemo.App.CHANNEL_1_ID;
import static com.example.alarmmanagerdemo.App.CHANNEL_2_ID;

public class MyAlarm extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        //notification channel 1
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_alert) //sets icon in notificaiton
                .setContentTitle("REMINDER CH 1")//title of the notification
                .setContentText("Hey this is channel 1 "); // Body text
                //.setPriority(NotificationCompat.PRIORITY_HIGH); // PRIORITY_HIGH -- makes phone ring and vibrate with notification popup


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1,builder.build());

        try {  // so that both notification does not occur at the same time.
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //notification channel 2
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context,CHANNEL_2_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("REMINDER CH 2")
                .setContentText("Hey this is channel 2 ");
                //.setPriority(NotificationCompat.PRIORITY_LOW);  // PRIORITY_LOW -- Does not make notification popup and ring + vibrate
        notificationManager.notify(2,builder1.build());

    }
}