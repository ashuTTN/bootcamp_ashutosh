package com.example.galleryappmvvm.view.activities


import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.view.fragments.LoginFragment

private var TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 101
    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private var appPermissions = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyPermission()
    }

    private fun verifyPermission() {
        //asking user for perimissions
        val permissions = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
        )
        //check if permissions already granted
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                permissions[0]
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this.applicationContext,
                permissions[1]
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this.applicationContext,
                permissions[2]
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            addLoginFragment()
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)    //if not request again
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        var flag = 0
        var flag1 = 0
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                //denied
                //Ask again
                flag1 = 1
            } else {
                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
                ) {
                    //permission allowed
                    Log.e("allowed", permission);
                } else {
                    //set to never ask again
                    Log.e("set to never ask again", permission);
                    //do something here.
                    flag = 1
                }
            }
        }
        if(flag==0){   // all permissions allowed
            addLoginFragment()
        }
        if(flag == 1){ //Some or all permissions denied
            AlertDialog.Builder(this)
                .setTitle("Permission Request")
                .setMessage("This app cannot function without the requested permissions please provide the permissions manually by going to [Settings] -> [Apps] ")
                .setPositiveButton("OK",DialogInterface.OnClickListener { dialog, which ->
                    startActivity( Intent(Settings.ACTION_SETTINGS))
                })
                .setCancelable(false)
                .show()
        }
        if(flag1 == 1){ //  Never ask again ..
            AlertDialog.Builder(this)
                .setTitle("Permission Request")
                .setMessage("These permissions are required for saving and capturing photos for your gallery the app cannot function without the asked permissions ")
                .setPositiveButton("OK",DialogInterface.OnClickListener { dialog, which ->
                    ActivityCompat.requestPermissions(this,permissions, requestCode)
                })
                .setCancelable(false)
                .show()
        }


    }
    //all permissions granted login fragment is added..
    private fun addLoginFragment() {
        fragment = LoginFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
