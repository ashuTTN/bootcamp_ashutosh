package com.example.galleryappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.galleryappmvvm.R
import com.example.gallleryapp1.View.CategoryFragment
import kotlinx.android.synthetic.main.activity_category.*


class CategoryActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var selectedFragment:Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        bottom_nav_bar.setOnNavigationItemSelectedListener {

            if (it.itemId == R.id.nav_category)
                selectedFragment = CategoryFragment()

            if (it.itemId == R.id.nav_timeline)
                selectedFragment = TimelineFragment()

            if (it.itemId == R.id.nav_user)
                selectedFragment = UserProfileFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, selectedFragment).commit()

            true
        }
        addCategoryFragment()
    }

    private fun addCategoryFragment() {
        fragment = CategoryFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container2, fragment)
        fragmentTransaction.commit()
    }
}
