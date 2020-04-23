package com.example.galleryappmvvm.view

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.galleryappmvvm.R

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermissions()
        addLoginFragment()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        if(permission != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"Permission to camera denied")
            makePermissionRequest()
        }
    }
    private var CAMERA_REQUEST_CODE = 101
    private fun makePermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG,"Permission has been denied by user")
                }
                else{
                    Log.d(TAG,"Permission has been granted by user")
                }
            }
        }
    }

    private fun addLoginFragment() {
        fragment = LoginFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

}
