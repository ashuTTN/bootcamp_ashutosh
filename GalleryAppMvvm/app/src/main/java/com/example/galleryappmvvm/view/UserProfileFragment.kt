package com.example.galleryappmvvm.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.userprofile_fragment_layout.*
import kotlinx.android.synthetic.main.userprofile_fragment_layout.view.*
import javax.sql.DataSource
import com.bumptech.glide.request.target.Target
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import com.example.galleryappmvvm.viewmodel.UserProfileViewModel

class UserProfileFragment : Fragment() {
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(UserProfileViewModel::class.java)
    }
    private lateinit var loadingDialog: LoadingDialog


    private var selectedPhotoUri: Uri? = null
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
        loadingDialog = LoadingDialog(activity!!)

        setObservers()
        setListeners(view)

        return view
    }

    private fun setObservers() {

        mViewModel.fetchUserDetails().observe(viewLifecycleOwner, Observer {
            text_username.text = it.get("name").toString()
            text_email.text = it.get("email").toString()
            userProfileImageUrl = it.get("profileImage").toString()
            image_view_userprofile_progress.visibility = View.VISIBLE
            Glide.with(this).load(userProfileImageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image_view_userprofile_progress.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                        return false
                    }
                })
                .into(image_view_userprofile)
        })


        mViewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity!!, it.toString(), Toast.LENGTH_LONG).show()
        })
        mViewModel.getUserProfileStatus().observe(viewLifecycleOwner, Observer {
            when (it) {
                UserProfileViewModel.Status.COMPLETE -> loadingDialog.dismissDialog()
                UserProfileViewModel.Status.HIDE_PROGRESS -> loadingDialog.dismissDialog()
                UserProfileViewModel.Status.SHOW_PROGRESS -> loadingDialog.startLoadingAnimation()
            }
        })

    }



    private fun setListeners(view: View) {
        view.btn_logout.setOnClickListener {
            mViewModel.logout()
            startActivity(Intent(activity,MainActivity::class.java))
            activity!!.finish()
        }

        view.edit_profile_image_fab.setOnClickListener {
            val d = Dialog(activity!!)
            d.setContentView(R.layout.image_picker_dialog)
            d.setTitle("Add Content")
            d.show()
            d.setTitle("Select a Method")
            val openCamBtn = d.findViewById(R.id.open_camera_button) as Button
            openCamBtn.setOnClickListener {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
                d.cancel()
            }
            val openGalleryBtn = d.findViewById(R.id.open_gallery_button) as Button
            openGalleryBtn.setOnClickListener {
                val galleryIntent = Intent(Intent.ACTION_PICK)
                galleryIntent.type = "image/*"
                startActivityForResult(galleryIntent, GALLERY_REQUEST)
                d.cancel()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val photo = data?.extras?.get("data") as Bitmap
            val path = MediaStore.Images.Media.insertImage(context!!.contentResolver, photo, "Title", null)
            selectedPhotoUri = Uri.parse(path)
            Log.d(TAG, "$selectedPhotoUri")
            view!!.image_view_userprofile.setImageURI(selectedPhotoUri)
            mViewModel.updateUserProfile(selectedPhotoUri!!)
        }
        if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            Glide.with(view!!).load(selectedPhotoUri).into(view!!.image_view_userprofile)
            mViewModel.updateUserProfile(selectedPhotoUri!!)
        }
    }
}