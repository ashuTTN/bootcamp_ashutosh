package com.example.fragmentexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String COMMON_TAG = " common-lifecycle ";
    private static final String TAG = " Activity ";
    Button addButton;
    Button replaceButton;
    Button hideButton;
    Button showButton;
    Button removeButton;

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.addButton);
        replaceButton = findViewById(R.id.replaceButton);
        hideButton = findViewById(R.id.hideButton);
        showButton = findViewById(R.id.showButton);
        removeButton = findViewById(R.id.removeButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.addButton:
                        addFragment();
                        break;
                    case R.id.replaceButton:
                        replaceFragment();
                        break;
                    case R.id.hideButton:
                        hideFragment();
                            break;
                    case R.id.removeButton:
                        removeFragment();
                        break;
                    case R.id.showButton:
                        showFragment();
                        break;
                }
            }
        };
        addButton.setOnClickListener(listener);
        replaceButton.setOnClickListener(listener);
        hideButton.setOnClickListener(listener);
        showButton.setOnClickListener(listener);
        removeButton.setOnClickListener(listener);
    }

    private void showFragment() {


    }

    private void removeFragment() {
        Fragment F = fm.findFragmentById(R.id.frameLayout);
        if(fragment!=null){
            FragmentTransaction removeft = fm.beginTransaction();
            removeft.remove(F);
            removeft.commit();
        }
        else{
            Toast.makeText(this,"empty",Toast.LENGTH_LONG).show();
        }
//        FragmentTransaction removeft;
//        removeft = fm.beginTransaction();
//        F = fm.findFragmentByTag(fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName());
//        Log.d("remove",fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName());
//        removeft.remove(F);
//        removeft.commit();
    }

    private void hideFragment() {
        FragmentTransaction hideft;
        hideft = fm.beginTransaction();
        Fragment topFrag = fm.findFragmentById(R.id.frameLayout);
        hideft.hide(topFrag);
        hideft.commit();
    }

    private void replaceFragment() {
        FragmentTransaction replaceft;
        replaceft = fm.beginTransaction();
        fragment = new Fragment4();
        replaceft.replace(R.id.frameLayout,fragment,"replace");
        replaceft.commit();
    }

    private void addFragment() {
        FragmentTransaction addft;
        String fragTag;
        int x = fm.getBackStackEntryCount();
        Log.d(TAG, "addFragment: "+ x);
        switch (fm.getBackStackEntryCount()){
            case 0: fragment = new Fragment1();fragTag = "fragment1";
            break;
            case 1: fragment = new Fragment2();fragTag = "fragment2";
            break;
            case 2: fragment = new Fragment3();fragTag = "fragment3";
                break;
            case 3: fragment = new Fragment4();fragTag = "fragment4";
                break;
            default:
                Toast.makeText(this,"reached end",Toast.LENGTH_LONG).show();
                return;
        }
        addft = fm.beginTransaction();
        addft.add(R.id.frameLayout,fragment,fragTag);
        addft.addToBackStack(fragTag);
        addft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(COMMON_TAG,TAG+"onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(COMMON_TAG,TAG+"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(COMMON_TAG,TAG+"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(COMMON_TAG,TAG+"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(COMMON_TAG,TAG+"onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(COMMON_TAG,TAG+"onRestart");
    }
}
