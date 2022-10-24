package com.example.threadringz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import kotlin.random.Random

class HandlerActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    val myRun = Runnable {
        findViewById<Button>(R.id.button).setText("dorin " + Random.nextInt(1000))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        findViewById<Button>(R.id.button).setOnClickListener {
            //back pressure
            handler.removeCallbacks(myRun)
            handler.postDelayed(myRun, 3000)
        }
    }
}