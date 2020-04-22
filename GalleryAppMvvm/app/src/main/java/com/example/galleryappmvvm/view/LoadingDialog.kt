package com.example.galleryappmvvm.view


import android.app.Activity
import android.app.AlertDialog
import com.example.galleryappmvvm.R

class LoadingDialog(_activity: Activity){
    private var activity = Activity()
    private lateinit var dialog:AlertDialog
    init {
        this.activity = _activity
    }

    fun startLoadingAnimation(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog,null))
        builder.setCancelable(true)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }
}