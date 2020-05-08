package com.example.galleryappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.galleryappmvvm.R
import kotlinx.android.synthetic.main.activity_category.*


class CategoryActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        bottomNavBarListener()
    }

    //setOnNaviagtionItemSelectedListener
    private fun bottomNavBarListener() {
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_category)
                selectedFragment = CategoryFragment()

            if (it.itemId == R.id.nav_timeline)
                selectedFragment = TimelineFragment()

            if (it.itemId == R.id.nav_user)
                selectedFragment = UserProfileFragment()

            val fragmentManager = supportFragmentManager
            if(fragmentManager.backStackEntryCount == 0){
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container2, selectedFragment,"selectedFrag")
                fragmentTransaction.commit()
            } else{
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container2, selectedFragment)
                fragmentTransaction.commit()
            }

            true
        }
        addCategoryFragment()
    }

    //initial fragment viewed in Category Activity
    private fun addCategoryFragment() {
        fragment = CategoryFragment()
        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container2, fragment)
        fragmentTransaction.commit()
    }
}
