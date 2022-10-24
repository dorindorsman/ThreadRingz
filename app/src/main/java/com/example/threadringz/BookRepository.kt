package com.example.threadringz

import android.util.Log
import java.net.URL

class BookRepository {
    fun loadBook(url: URL) {
        Thread {
            Thread.sleep(500)
            Log.d("threads11", "loading book")
        }.start()
    }
}