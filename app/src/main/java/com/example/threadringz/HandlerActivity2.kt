package com.example.threadringz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import kotlin.random.Random

class HandlerActivity2 : AppCompatActivity() {

    private val PUSH_BUTTON = 100


    private val messageHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            findViewById<Button>(R.id.btnTask).text = msg.obj as String
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler2)

        btnClickListener()

    }

    private fun btnClickListener() {
        findViewById<Button>(R.id.btnUpLeft).setOnClickListener {
            messageHandler.removeMessages(PUSH_BUTTON)
            messageHandler.sendMessageDelayed(messageHandler.obtainMessage(PUSH_BUTTON, (it as Button).text),3000)
        }

        findViewById<Button>(R.id.btnUpRight).setOnClickListener {
            it as Button
            messageHandler.removeMessages(PUSH_BUTTON)
            messageHandler.sendMessageDelayed(messageHandler.obtainMessage(PUSH_BUTTON, it.text),3000)
        }

        findViewById<Button>(R.id.btnDownLeft).setOnClickListener {
            it as Button
            messageHandler.removeMessages(PUSH_BUTTON)
            messageHandler.sendMessageDelayed(messageHandler.obtainMessage(PUSH_BUTTON, it.text),3000)
        }

        findViewById<Button>(R.id.btnDownRight).setOnClickListener {
            it as Button
            messageHandler.removeMessages(PUSH_BUTTON)
            messageHandler.sendMessageDelayed(messageHandler.obtainMessage(PUSH_BUTTON, it.text),3000)
        }

    }
}