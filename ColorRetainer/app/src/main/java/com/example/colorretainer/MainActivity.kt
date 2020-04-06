package com.example.colorretainer

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var colorChangerViewModel: ColorGenerator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        colorChangerViewModel = ViewModelProviders.of(this).get(ColorGenerator::class.java)
        consLayout.setBackgroundColor(colorChangerViewModel.getColorResource())
        changeButton.setOnClickListener {
            val color = generateRandomColor()
            consLayout.setBackgroundColor(color)
            colorChangerViewModel.setColorResource(color)
        }
    }
    private fun generateRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }}
