package com.example.notificationbouns

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, CakeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        button.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
                var builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Cake Cooking Timer")
                    .setContentText("Cake is Ready!")
                notificationManager.notify(1234, builder.build())
            } else {
                var builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Cake Cooking Timer")
                    .setContentText("Cake is Ready!")
                notificationManager.notify(1234, builder.build())
            }

        }
    }
}