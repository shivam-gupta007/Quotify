package com.app.dailymotivation.feature_quote.presentation.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)

fun createNotificationChannel(context: Context) {
    val name = "My Notification Channel"
    val description = "A description of channel"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(channelId, name, importance)
    channel.description = description
    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

@RequiresApi(Build.VERSION_CODES.N)
fun scheduleNotification(context: Context) {
    val intent = Intent(context, Notification::class.java)
    intent.putExtra(titleExtra, "Fresh Motivational Quotes")
    intent.putExtra(messageExtra, "Motivate yourself from the quotes")

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        notificationId,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val time = getTime()

    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        time,
        pendingIntent
    )
}

@RequiresApi(Build.VERSION_CODES.N)
fun getTime(): Long {
    val minute = 18
    val hour = 11
    val day = 2
    val month = 5
    val year = 2022

    val calendar = Calendar.getInstance()
    calendar.set(year,month,day,hour,minute)

    return calendar.timeInMillis
}
