package com.example.fragmentpart4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.ClipData;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putInt("itemPosition", 0);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ItemFragment itemFragment = new ItemFragment();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        ft.replace(R.id.itemFrame,itemFragment,"Item Fragment");
        ft.replace(R.id.detailFrame,detailFragment,"detail");
        ft.commit();

    }
}