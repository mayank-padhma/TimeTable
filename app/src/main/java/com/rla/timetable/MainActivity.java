package com.rla.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView timetable = findViewById(R.id.imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myNotification", "myNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "You have a period in next 10 minutes.";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "myNotification")
                        .setSmallIcon(R.drawable.book)
                        .setContentTitle("Next Period")
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

                Intent intent = new Intent(MainActivity.this,
                        notification.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(
                        NOTIFICATION_SERVICE
                );
                notificationManager.notify(0, builder.build());
            }
        });


    }
}