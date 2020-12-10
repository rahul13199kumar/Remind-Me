package com.rnr.remindme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    private int notificationId;

    @Override
    public void onReceive(Context context, Intent intent) {

        //Get Id
        int notification = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("todo");

        //when notification is tapped.
        Intent mainIntent = new Intent(context, com.rnr.remindme.MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent,0);

        NotificationManager myNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //prepare notification
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("it's Time!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);
        
        myNotificationManager.notify(notificationId, builder.build());

    }
}
