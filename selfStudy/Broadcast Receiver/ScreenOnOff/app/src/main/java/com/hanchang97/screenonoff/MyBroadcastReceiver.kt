package com.hanchang97.screenonoff

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action.equals(Intent.ACTION_SCREEN_ON)){
            Log.d(TAG, "ACTION_SCREEN_ON")
            Toast.makeText(context, "ACTION_SCREEN_ON", Toast.LENGTH_LONG).show()
        } else if(intent.action.equals(Intent.ACTION_SCREEN_OFF)){
            Log.d(TAG, "ACTION_SCREEN_OFF")
            Toast.makeText(context, "ACTION_SCREEN_OFF", Toast.LENGTH_LONG).show()
        } else if(intent.action.equals("example.broadcast.custom.test")){
            Log.d(TAG, "example.broadcast.custom.test")
            Toast.makeText(context, "example.broadcast.custom.test", Toast.LENGTH_LONG).show()
        }


       /* StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }*/
    }
}