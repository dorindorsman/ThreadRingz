package com.example.threadringz

import android.util.Log

class LazyTest {

    val d1 = Person("dorin1", "dorsman1")
    val d2 by lazy { Person("dorin2", "dorsman2") }

}


class Person(private val name: String, private val lastName: String) {
    init {
        Log.d("Person", "Name : {$name $lastName}")
    }

}