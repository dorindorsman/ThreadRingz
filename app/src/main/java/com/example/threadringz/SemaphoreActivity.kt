package com.example.threadringz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Semaphore

class SemaphoreActivity : AppCompatActivity() {

    var mSemaphore = Semaphore(1)


    @Throws(InterruptedException::class)
    fun doJob(id: String) {
        try {
            mSemaphore.acquire()
            Log.d("threadjob", "starting: $id")
            Thread.sleep(2000)
            runOnUiThread {  findViewById<Button>(R.id.btnTask).text = id}
            } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d("threadjob", "ending: $id")
        mSemaphore.release()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaphore)

        btnClickListener()

    }

    private fun btnClickListener() {
        findViewById<Button>(R.id.btnUpLeft).setOnClickListener {
            it as Button
            Thread{
                doJob(it.text.toString())
            }.start()
        }

        findViewById<Button>(R.id.btnUpRight).setOnClickListener {
            it as Button
            Thread{
                doJob(it.text.toString())
            }.start()
        }

        findViewById<Button>(R.id.btnDownLeft).setOnClickListener {
            it as Button
            Thread{
                doJob(it.text.toString())
            }.start()
        }

        findViewById<Button>(R.id.btnDownRight).setOnClickListener {
            it as Button
            Thread{
                doJob(it.text.toString())
            }.start()
        }
    }
}