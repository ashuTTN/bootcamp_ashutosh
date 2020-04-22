package com.example.galleryappmvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.userprofile_fragment_layout.*
import kotlinx.android.synthetic.main.userprofile_fragment_layout.view.*

class UserProfileFragment : Fragment() {
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
        view.btn_logout.setOnClickListener {
            viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
            viewModel.logout()
            startActivity(Intent(activity, MainActivity::class.java))
        }

        viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        val loadingDialog = LoadingDialog(activity!!)
        loadingDialog.startLoadingAnimation()
        viewModel.fetchUserDetails()
            .addOnSuccessListener {
                user_info_heading.text = "Hello,\n${it.get("name")}"
                text_username.text = it.get("name").toString()
                text_email.text = it.get("email").toString()
                userProfileImageUrl = it.get("profileImage").toString()
                Glide.with(this).load(userProfileImageUrl).into(image_view_userprofile);
                loadingDialog.dismissDialog()
            }

        Log.d(TAG, "$savedUser")
        return view
    }
}