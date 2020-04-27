package com.example.galleryappmvvm.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.signup_fragment_layout.*
import kotlinx.android.synthetic.main.signup_fragment_layout.view.*

class SignUpFragment : Fragment() {
    private val TAG = "SIGNUP_FRAGMENT"
    private lateinit var viewModel: FirebaseViewModel
    private var selectedPhotoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.signup_fragment_layout, container, false)
        Log.d(TAG,"SIGNING UP ")
        view.image_view_signup.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        view.btn_login_signup.setOnClickListener {

            val name = name_txt_signup.text.toString().trim()
            val email = email_txt_signup.text.toString().trim()
            val pass = password_txt_signup.text.toString()
            if (selectedPhotoUri == null) {
                Toast.makeText(activity, "Please select a profile image", Toast.LENGTH_LONG).show()
            }
            if (name.isEmpty()) {
                name_txt_signup.error = "Name can't be empty"
            }
            if (pass.isEmpty()) {
                password_txt_signup.error = "Password can't be empty"
            }
            if (email.isEmpty()) {
                email_txt_signup.error = "Email can't be empty"
            }
            if (!(email.isEmpty() || pass.isEmpty() || name.isEmpty() || selectedPhotoUri == null)) {
                viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

                viewModel.signup(
                    name_txt_signup.text.toString(),
                    email_txt_signup.text.toString(),
                    password_txt_signup.text.toString(),
                    selectedPhotoUri!!
                )

                startActivity(Intent(activity, MainActivity::class.java))
            }
        }
        view.btn_already_existing.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            Log.d(TAG, "$selectedPhotoUri")
            //for large iamge setIamgeURI does not work
            //image_view_signup.setImageURI(selectedPhotoUri)
            Glide
                .with(this)
                .load(selectedPhotoUri)
                .into(image_view_signup)
        }
    }
}