package com.deepshikhayadav.notificationtesting

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.text.style.BulletSpan
import android.util.Log
import androidx.core.app.NotificationCompat

class MyAlarm : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        try{
            showNotification(context,"First notification","compose notification")
        }
        catch (e:Exception){
            Log.d("error","onReceive ${e.printStackTrace()}")
        }
    }

    private fun showNotification(context: Context?, title: String, desc: String) {
    val manager=context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId="Message Channel"
        val channelName="message Name"
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder= NotificationCompat.Builder(context,channelId)
            .setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        manager.notify(1,builder.build())
    }

    override fun peekService(myContext: Context?, service: Intent?): IBinder {
        return super.peekService(myContext, service)
    }
}