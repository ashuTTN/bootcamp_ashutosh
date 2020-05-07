package com.example.galleryappmvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import com.example.galleryappmvvm.viewmodel.LoginViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import kotlinx.android.synthetic.main.login_fragment_layout.*
import kotlinx.android.synthetic.main.login_fragment_layout.view.*


class LoginFragment : Fragment() {
    private lateinit var viewModel: FirebaseViewModel
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(LoginViewModel::class.java)
    }
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment_layout, container, false)
        loadingDialog = LoadingDialog(activity!!)

        setObservers()
        setListeners(view!!)
        return view
    }

    private fun setListeners(view: View) {
        view.btn_login.setOnClickListener {
            onLogInClicked()
        }
        view.btn_sign_up.setOnClickListener {
            addSignUpFragment()
        }
    }

    private fun onLogInClicked() {
        mViewModel.onLogInClicked(email_txt.text.toString(), password_txt.text.toString())
    }

    private fun setObservers() {
        mViewModel.getErrorState().observe(viewLifecycleOwner, Observer {
            when(it){
                LoginViewModel.ErrorState.EMAIL_BLANK -> email_txt.setError("Email can't be blank")
                LoginViewModel.ErrorState.PASSWORD_BLANK -> password_txt.setError("Password can't be blank ")
                LoginViewModel.ErrorState.EMAIL_PASSWORD_BLANK ->{
                    email_txt.setError("Email can't be blank")
                    password_txt.setError("Password can't be blan")
                }
            }

        })
        mViewModel.getErrMessage().observe(viewLifecycleOwner, Observer {
            error_text_view.setText(it)
            Toast.makeText(view!!.context, it, Toast.LENGTH_LONG).show()
        })
        mViewModel.getSignInState().observe(viewLifecycleOwner, Observer {
            when (it) {
                LoginViewModel.SignInState.SHOW_PROGRESS ->
                    showProgress()
                LoginViewModel.SignInState.HIDE_PROGRESS ->
                    hideProgress()
                LoginViewModel.SignInState.GO_TO_CATEGORY_SCREEN ->
                    goToCategoryScreen()
            }
        })
    }

    private fun goToCategoryScreen() {
        val intent = Intent(activity, CategoryActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

    private fun hideProgress() {
        loadingDialog.dismissDialog()
    }

    private fun showProgress() {
        loadingDialog.startLoadingAnimation()
    }


    private fun addSignUpFragment() {
        val nextFrag = SignUpFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextFrag, "signup_fragment")
            .addToBackStack("SignUpFragment")
            .commit()
    }
}