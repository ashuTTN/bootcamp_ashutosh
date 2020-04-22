package com.example.galleryappmvvm.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.image_picker_dialog.*
import kotlinx.android.synthetic.main.image_picker_dialog.view.*
import kotlinx.android.synthetic.main.userprofile_fragment_layout.*
import kotlinx.android.synthetic.main.userprofile_fragment_layout.view.*

class UserProfileFragment : Fragment() {
    private val CAMERA_REQUEST = 0
    private val GALLERY_REQUEST = 1
    private val TAG = "UserProfileFragment"
    private lateinit var viewModel: FirebaseViewModel
    private var userProfileImageUrl: String? = ""
    private var savedUser = mutableMapOf<String, String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.userprofile_fragment_layout, container, false)

        viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        val loadingDialog =
            LoadingDialog(activity!!)
        loadingDialog.startLoadingAnimation()
        viewModel.fetchUserDetails()
            .addOnSuccessListener {
                text_username.text = it.get("name").toString()
                text_email.text = it.get("email").toString()
                userProfileImageUrl = it.get("profileImage").toString()
                Glide.with(this).load(userProfileImageUrl).into(image_view_userprofile);
                loadingDialog.dismissDialog()
            }

        Log.d(TAG, "$savedUser")


        view.btn_logout.setOnClickListener {
            viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
            viewModel.logout()
            startActivity(Intent(activity, MainActivity::class.java))
        }
        view.edit_profile_image_fab.setOnClickListener{
            val d = Dialog(activity!!)
            d.setContentView(R.layout.image_picker_dialog)
            d.setTitle("Add Content")
            d.show()
            var btn1 = d.findViewById(R.id.button1) as Button
            btn1.setOnClickListener {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent,CAMERA_REQUEST)
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            val photo = data?.extras?.get("data") as Bitmap
            view!!.image_view_userprofile.setImageBitmap(photo)
        }
    }
}