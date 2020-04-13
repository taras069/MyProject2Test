package com.example.comp_admin.a30simplenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID: String = "personal_info"
    private val NOTIFICATION_ID: Int = 1

    var text: String = "he Big Billion Days sale will roll out in a phased manner, like previous sales. The first day of the sale will include offers on fashion, TVs and appliances, furniture, smart devices, and other product categories. The second day of the sale will unlock deals on smartphones and other electronics. The last three days of the sale will include offers across all product categories. In addition to regular discounts, Flipkart will also host flash sales every hour and new deals will be offered every eight hours, according to the company. The company will be offering discounts of up to 80 percent on TVs and other electronics, while 90 percent off will on home decor, fashion, and personal care products"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_simple.setOnClickListener {
            createSimpleNotification()
        }
    }

    private fun createSimpleNotification() {
        createNotificationChannel()

        var intent = Intent(this, LandingActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var pendingIntent = PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_ONE_SHOT)

        var yesIntent = Intent(this, YesActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var pendingYesIntent = PendingIntent.getActivities(this, 0, arrayOf(yesIntent), PendingIntent.FLAG_ONE_SHOT)

        var noIntent = Intent(this, NoActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var pendingNoIntent = PendingIntent.getActivities(this, 0, arrayOf(noIntent), PendingIntent.FLAG_ONE_SHOT)


        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.images)




        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setSmallIcon(R.drawable.ic_phone_black_24dp)
            .setContentTitle("Notification Title")
            .setContentText("This is a notification description")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_check_black_24dp, "Yes", pendingYesIntent)
            .addAction(R.drawable.ic_close_black_24dp, "No", pendingNoIntent)
        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var name = "Personal Information"
            var description = "include all the personal information"
            var importance = NotificationManager.IMPORTANCE_DEFAULT
            var notificationChannel = NotificationChannel(CHANNEL_ID, name, importance)
            notificationChannel.description = description

            var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
