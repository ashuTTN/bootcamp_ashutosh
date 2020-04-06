package com.example.colorretainer

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class ColorGenerator : ViewModel() {
    private var colorResource: Int = 0xfff
    fun setColorResource(colorResource: Int) {
        this.colorResource = colorResource
    }
    fun getColorResource() = colorResource

}