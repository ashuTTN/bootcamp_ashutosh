package com.example.galleryappmvvm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.login_fragment_layout.*
import kotlinx.android.synthetic.main.login_fragment_layout.view.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: FirebaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment_layout, container, false)
        view.btn_login.setOnClickListener {
            val email = email_txt.text.toString()
            val pass = password_txt.text.toString()

            if (email.isEmpty()) {
                email_txt.setError("Email can't be blank")
            }
            if (pass.isEmpty()) {
                password_txt.setError("Password can't be blank")
            }
            if (!(email.isEmpty() || pass.isEmpty())) {
                val loadingDialog = LoadingDialog(activity!!)
                loadingDialog.startLoadingAnimation()
                viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
                viewModel.login(email, pass)
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Login Successfull", Toast.LENGTH_LONG).show()
                        addCategoryActivity()
                        loadingDialog.dismissDialog()
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "Login failed ${it.message}", Toast.LENGTH_LONG)
                            .show()
                    }
            }

        }

        view.btn_sign_up.setOnClickListener {
            addSignUpFragment()
        }

        return view
    }

    private fun addCategoryActivity() {
        val intent = Intent(activity, CategoryActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

    private fun addSignUpFragment() {
        val nextFrag = SignUpFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextFrag, "signup_fragment")
            .addToBackStack(null)
            .commit()
    }
}