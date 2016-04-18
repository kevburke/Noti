package com.example.Noti;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


public class MyActivity extends Activity {

    private static final int NOTE_ID = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    public void Confirm(View v) {
        //Run 10 seconds after click
        handler.postDelayed(task, 10000);
        Toast.makeText(this, "Notification will post in 10 seconds", Toast.LENGTH_SHORT).show();
        handler.postDelayed(task, 15000);
        Toast.makeText(this, "Notification will post in 10 seconds", Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Intent launchIntent = new Intent(getApplicationContext(), Receiver.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, launchIntent, 0);
            Notification.Builder builder = new Notification.Builder(MyActivity.this);
            //Set notification information
            builder.setSmallIcon(R.drawable.ic_launcher)
                    .setTicker("Something Happened")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setContentTitle("We're Finished!")
                    .setContentText("Click Here!")
                    .setContentIntent(contentIntent);

            Notification.BigPictureStyle style= new Notification.BigPictureStyle(builder);
            style.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
            Notification note = builder.build();
            manager.notify(NOTE_ID, note);
        }
    };
}
