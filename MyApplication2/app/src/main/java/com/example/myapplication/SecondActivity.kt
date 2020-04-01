package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var usrname2: TextView
    lateinit var password2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        usrname2 = findViewById(R.id.username2)
        password2 = findViewById(R.id.password2)
        val a = intent.getStringExtra("username")
        val b = intent.getStringExtra("password")
        password2.text = b
        usrname2.text = a
    }
}