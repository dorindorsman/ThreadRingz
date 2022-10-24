package com.example.threadringz

import android.annotation.SuppressLint
import android.os.*
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

@SuppressLint("HandlerLeak")
class MainActivity : AppCompatActivity() {

    private var queueThread: QueueThread? = null

    private val uiHandler = Handler()

    private val messageHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 100) {
                Log.d("mhandler", "download picture")
            }

            if (msg.what == 200) {
                Log.d("mhandler", "download song")
            }

            if (msg.what == 300) {
                findViewById<Button>(R.id.btnStop).setText(msg.obj as String)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val didi = LazyTest()
//        didi.d2

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            queueThread = QueueThread()
            queueThread?.start()
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            queueThread?.stopAll()
            queueThread = null
        }

        findViewById<Button>(R.id.btnTask).setOnClickListener {
            queueThread?.addTask(Random.nextInt(1000))
        }

//        val counter = SemaphoreCounter()
//        inc(counter)
//        inc(counter)
//        inc(counter)
//        inc(counter)

//        val jobWorker = JobWorker()
//        doJob(jobWorker, "1")
//        doJob(jobWorker, "2")
//        doJob(jobWorker, "3")
//        doJob(jobWorker, "4")

//        val queueThreadPool = QueueThreadPool()
//        repeat(10) {
//            queueThreadPool.addTask(Random.nextInt())
//        }

//        val handler = Handler(Looper.getMainLooper())
//        val handler2 = Handler(Looper.getMainLooper())
//
//        handler.post(Runnable {
//            Log.d("handlert", "s1")
//            Thread.sleep(500)
//            Log.d("handlert", "e1")
//        })
//
//        handler2.post {
//            Log.d("handlert", "s3")
//            Thread.sleep(500)
//            Log.d("handlert", "e3")
//        }
//
//        newHandler()

        //looperThread()

        messageHandler.sendEmptyMessage(100)
        messageHandler.sendEmptyMessage(200)
        messageHandler.sendEmptyMessage(100)

        messageHandler.sendEmptyMessageDelayed(200, 3000)

        messageHandler.sendMessage(messageHandler.obtainMessage(300, "blabla"))
        messageHandler.sendMessageDelayed(messageHandler.obtainMessage(300, "blabla delayed"), 5000)
    }

    fun newLooperThread() {
        // make this thread work with looper and create a handler with this thread's looper
        var mPriority = 0
        var mTid = -1
        var mLooper: Looper? = null
        val t = Thread{
            mTid = Process.myTid()
            Looper.prepare()
            synchronized(this) {
                mLooper = Looper.myLooper()
                //notifyAll()
            }
            Process.setThreadPriority(mPriority)
            //onLooperPrepared()
            Looper.loop()
            mTid = -1

        }

        t.start()

        val dorinHandler = Handler()
    }

    fun looperThread() {
        val coolThread = HandlerThread("coolThread")
        coolThread.start()

        val coolHandler = Handler(coolThread.looper)

        val task3 = Runnable {
            Log.d("handlert", "${Thread.currentThread().name} s3")
            Thread.sleep(500)
            Log.d("handlert", "e3")

            uiHandler.post {
                findViewById<Button>(R.id.btnTask).setText("foo2")
            }
        }

        coolHandler.post {
            Log.d("handlert", "${Thread.currentThread().name} s1")
            Thread.sleep(500)
            Log.d("handlert", "e1")
        }

        coolHandler.post {
            Log.d("handlert", "${Thread.currentThread().name} s2")
            Thread.sleep(500)
            Log.d("handlert", "e2")

            runOnUiThread {
                findViewById<Button>(R.id.btnTask).text = "foo1"
            }

            coolHandler.removeCallbacks(task3)
        }

        coolHandler.post(task3)
    }

    fun newHandler() {
        Thread {
            Thread.sleep(5000)
            runOnUiThread { }
            uiHandler.post {
                findViewById<Button>(R.id.btnTask).text = "foo"
            }
            Handler(Looper.getMainLooper())
        }.start()
    }

    fun doJob(jobWorker: JobWorker, id: String) {
        Thread {
            jobWorker.doJob(id)
        }.start()
    }

    fun inc(counter: SemaphoreCounter) {
        Thread {
            repeat(1000) {
                counter.increment()
            }
            Log.d("threadcount", "result: ${counter.value}")
        }.start()
    }
}