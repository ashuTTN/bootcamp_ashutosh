package com.example.galleryappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addLoginFragment()
    }

    private fun addLoginFragment() {
        fragment = LoginFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

}
