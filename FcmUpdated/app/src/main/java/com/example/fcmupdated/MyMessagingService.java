package com.example.fcmupdated;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyMessagingService extends FirebaseMessagingService {
    private static final String TAG = "ASHUTOSH";
    private static final int NOTIFICATION_ID =1 ;
    private String CHANNEL_ID = "Channel1";
    NotificationCompat.Builder builder;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String price;
        String imgURL;

        price = remoteMessage.getData().get("price");
        imgURL = remoteMessage.getData().get("url");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel",NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        Intent intent = new Intent(this,ItemScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("price",price);
        intent.putExtra("imgURL",imgURL);

        PendingIntent pendingIntent= PendingIntent.getActivity(this,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getImageFromURL((remoteMessage.getNotification().getImageUrl()).toString())));

        manager.notify(NOTIFICATION_ID,builder.build());

    }

    private Bitmap getImageFromURL(String imgURL) {
        try{
            Bitmap bitmap = Glide.with(this).asBitmap().load(imgURL).into(100, 100).get();
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }

}
