package com.yash10019coder.callrecording

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intentFilter = IntentFilter().apply {
            addAction("android.intent.action.PHONE_STATE")

        }
        var broadcastReceiver=CallBroadcastReceiver().apply {
            registerReceiver(this,intentFilter)
        }
//        Intent(this, CallBroadcastReceiver::class.java).apply {
//            sendBroadcast(this)
//        }
    }
}