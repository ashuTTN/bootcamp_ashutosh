package com.example.contacts2.model.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.contacts2.R
import com.example.contacts2.Utils.*

class NewPersonActivity : AppCompatActivity() {
    private lateinit var enteredName:EditText
    private lateinit var enteredPhone:EditText
    private lateinit var enteredEmail:EditText
    private lateinit var saveButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_person)

        enteredEmail=findViewById(R.id.enteredEmail)
        enteredName=findViewById(R.id.enteredName)
        enteredPhone=findViewById(R.id.enteredPhone)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val intent = Intent()
            if(TextUtils.isEmpty(enteredName.text) || TextUtils.isEmpty(enteredEmail.text) || TextUtils.isEmpty(enteredPhone.text)){
                setResult(RESULT_ERROR,intent)
            }
            else{
            intent.putExtra(EXTRA_ENTERED_NAME,enteredName.text.toString())
            intent.putExtra(EXTRA_ENTERED_PHONE,enteredPhone.text.toString())
            intent.putExtra(EXTRA_ENTERED_EMAIL,enteredEmail.text.toString())
            setResult(RESULT_SAVE, intent)
         }
            finish()
        }
    }
}
