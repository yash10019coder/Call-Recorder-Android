package com.yash10019coder.callrecording

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView

class CallBroadcastReceiver : BroadcastReceiver() {
    fun setText(textView: TextView, text: String) {
        var text1 = textView.text
        textView.text = text1
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        var textView = (context as MainActivity).findViewById<TextView>(R.id.textView)
        Log.i("tag", "onReceive: the received intent action is ${intent!!.action}")
        if (intent!!.action == Intent.ACTION_NEW_OUTGOING_CALL) {
            Log.i("tag", "onReceive: the received intent action is ${intent.action}")
            val phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            Log.i("tag", "onReceive: the received intent phone number is $phoneNumber")
            val callIntent = Intent(context, CallRecordingService::class.java)
            callIntent.putExtra("phoneNumber", phoneNumber)
            callIntent.putExtra("callType", "outgoing")
            context!!.startService(callIntent)
        } else {
            val tm = context!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            when (tm.callState) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    Log.i("tag", "onReceive: the received intent action is ${intent.action}")
                    val phoneNumber = intent.getStringExtra("incoming_number")
                    Log.i("tag", "onReceive: the received intent phone number is $phoneNumber")
                    val callIntent = Intent(context, CallRecordingService::class.java)
                    callIntent.putExtra("phoneNumber", phoneNumber)
                    callIntent.putExtra("callType", "incoming")
                    context!!.startService(callIntent)
                }
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    Log.i("tag", "onReceive: the received intent action is ${intent.action}")
                    val phoneNumber = intent.getStringExtra("outgoing_number")
                    Log.i("tag", "onReceive: the received intent phone number is $phoneNumber")
                    val callIntent = Intent(context, CallRecordingService::class.java)
                    callIntent.putExtra("phoneNumber", phoneNumber)
                    callIntent.putExtra("callType", "outgoing")
                    context!!.startService(callIntent)
                }
                else -> {
                    Log.i("tag", "onReceive: the received intent action is ${intent.action}")
                }
            }
        }
    }
}