package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();
    private static final String TAG = COMMON_TAG;
    Button button = findViewById(R.id.button);
    FragmentTransaction ft;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,ACTIVITY_NAME+" onCreate");
    }
//    private void addFragment(){
//        Fragment fragment;
//        switch(fm.getBackStackEntryCount()){
//            case 0:fragment = new SampleFragment();break;
//            case 1:fragment = new Fragment2();break;
//            case 2:fragment = new Fragment3();break;
//            default: fragment = new SampleFragment(); break;
//        }
//        ft = fm.beginTransaction();
//        ft.add(R.id.fragmentContainer, fragment,"demofragment");
//        ft.addToBackStack(null);
//        ft.commit();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, ACTIVITY_NAME+" onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, ACTIVITY_NAME+" onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, ACTIVITY_NAME+" onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, ACTIVITY_NAME+" onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, ACTIVITY_NAME+" onPause");
    }
}
