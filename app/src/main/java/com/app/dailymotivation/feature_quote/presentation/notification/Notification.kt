package com.app.dailymotivation.feature_quote.presentation.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.app.dailymotivation.R

const val notificationId = 1
const val channelId = "channel1"
const val titleExtra = "TitleExtra"
const val messageExtra = "MessageExtra"

class Notification: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notification = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(intent?.getStringExtra(titleExtra))
            .setContentText(intent?.getStringExtra(messageExtra))
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notification)
    }
}