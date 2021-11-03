package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_main_counting.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification(){
        runBlocking {
            launch {
                delay(5000)

                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                val intent = Intent(this@MainActivity, EGG::class.java)

                val pendingIntent = PendingIntent.getActivity(this@MainActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    val notificationChannel = NotificationChannel("Hello world", "getCoffee()", NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(notificationChannel)

                    val build = Notification.Builder(this@MainActivity, "Hello world")
                            .setSmallIcon(R.drawable.ic_baseline_egg_24)
                            .setLargeIcon(BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.ic_baseline_egg_24))
                            .setContentIntent(pendingIntent)
                            .setContentTitle("Egg Cooking Counter")
                            .setContentText("Ready")

                    notificationManager.notify(11, build.build())
                }
                else{
                    val build = Notification.Builder(this@MainActivity)
                            .setSmallIcon(R.drawable.ic_baseline_egg_24)
                            .setLargeIcon(BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.ic_baseline_egg_24))
                            .setContentIntent(pendingIntent)
                            .setContentTitle("Egg Cooking Counter")
                            .setContentText("Ready")

                    notificationManager.notify(11, build.build())
                }
            }
        }
    }
}