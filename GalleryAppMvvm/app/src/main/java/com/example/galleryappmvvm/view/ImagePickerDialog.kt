package com.example.galleryappmvvm.view


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import com.example.galleryappmvvm.R

class ImagePickerDialog(_activity: Activity){
    private var activity = Activity()
    private lateinit var dialog:AlertDialog
    init {
        this.activity = _activity
    }

    fun showImagePcikerDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.image_picker_dialog,null))
        val items = arrayOf("Camera","Gallery")

        builder.setCancelable(true)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }


}