package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        val context = this
        loginButton.setOnClickListener {

            if (username.text.length < 1)
                username.setError("Empty username")
            else if (password.text.length < 5)
                password.setError("password length should be greater than 5")
            else {
                Toast.makeText(this, "You clicked me.", Toast.LENGTH_SHORT).show()
                intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("username", username.text.toString())
                intent.putExtra("password", password.text.toString())
                startActivity(intent)
            }
        }
    }
}
