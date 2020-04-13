package com.example.comp_admin.a31firebasecloudmessaging

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseInstanceIdService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("aaa", "from: " + remoteMessage.from)
        Log.d("aaa", "body: " + remoteMessage.notification!!.body)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("fcm_message", remoteMessage.notification!!.body)
        startActivity(intent)
    }
}