package com.example.root.gni_transport.gni.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.root.gni_transport.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.example.root.gni_transport.gni.ui.activities.MainActivity;

/**
 * Created by root on 2/1/18.
 */

public class FirebasewMessage extends FirebaseMessagingService {
    public static final String TAG="firebaseMEssage";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = null, body = null, click_action = null;
        if (remoteMessage != null) {
            try {
                title = remoteMessage.getNotification().getTitle();
                body = remoteMessage.getNotification().getBody();
                click_action = remoteMessage.getNotification().getClickAction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent =new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,
                intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationbuilder = new
                NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService
                (Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationbuilder.build());
    }
}
