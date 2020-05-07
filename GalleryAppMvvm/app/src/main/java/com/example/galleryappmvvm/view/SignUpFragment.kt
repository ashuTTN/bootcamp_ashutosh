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
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import com.example.galleryappmvvm.viewmodel.LoginViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import com.example.galleryappmvvm.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.signup_fragment_layout.*
import kotlinx.android.synthetic.main.signup_fragment_layout.view.*
private const val TAG = "SIGNUP_FRAGMENT"
class SignUpFragment : Fragment() {
    private lateinit var loadingDialog: LoadingDialog
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(SignUpViewModel::class.java)
    }


    private var selectedPhotoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.signup_fragment_layout, container, false)
        loadingDialog = LoadingDialog(activity!!)

        setObservers()
        setListeners(view)

        return view
    }

    private fun setListeners(view: View) {
        view.btn_login_signup.setOnClickListener {
            onSignUpClicked()
        }
        view.btn_already_existing.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
        view.image_view_signup.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    private fun onSignUpClicked() {
        mViewModel.onSignUpClicked(
            name_txt_signup.text.toString(),
            email_txt_signup.text.toString(),
            password_txt_signup.text.toString(),
            selectedPhotoUri
        )
    }

    private fun setObservers() {
        mViewModel.getErrMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(view!!.context, it, Toast.LENGTH_LONG).show()
        })
        mViewModel.getSignUpState().observe(viewLifecycleOwner, Observer {
            when (it) {
                SignUpViewModel.SignUpState.SHOW_PROGRESS -> showProgress()
                SignUpViewModel.SignUpState.HIDE_PROGRESS -> hideProgress()
                SignUpViewModel.SignUpState.GO_TO_LOGIN_SCREEN -> goToLoginScreen()
            }
        })
        mViewModel.getErrState().observe(viewLifecycleOwner
        , Observer {
                when(it){
                    SignUpViewModel.ErrState.EMAIL_BLANK -> email_txt_signup.setError("Email can't be blank")
                    SignUpViewModel.ErrState.PASSWORD_BLANK -> password_txt_signup.setError("Password can't be blank")
                    SignUpViewModel.ErrState.NAME_BLANK -> name_txt_signup.setError("Name can't be blank")
                }
            })
    }

    private fun goToLoginScreen() {
        val intent = Intent(activity, CategoryActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

    private fun showProgress() {
        loadingDialog.startLoadingAnimation()
    }

    private fun hideProgress() {
        loadingDialog.dismissDialog()
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