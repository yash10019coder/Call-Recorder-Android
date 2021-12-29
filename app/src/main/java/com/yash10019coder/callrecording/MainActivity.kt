package com.yash10019coder.callrecording

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, CallBroadcastReceiver::class.java).apply {
            sendBroadcast(this)
        }
    }
}