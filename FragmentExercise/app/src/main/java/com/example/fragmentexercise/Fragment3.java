package com.example.fragmentexercise;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    private static final String COMMON_TAG = " common-lifecycle ";
    private static final String TAG = " Fragment3 ";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(COMMON_TAG,TAG+"onCreateView");
        return inflater.inflate(R.layout.fragment_layout3,container,false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(COMMON_TAG,TAG+"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(COMMON_TAG,TAG+"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(COMMON_TAG,TAG+"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(COMMON_TAG,TAG+"onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(COMMON_TAG,TAG+"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(COMMON_TAG,TAG+"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(COMMON_TAG,TAG+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(COMMON_TAG,TAG+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(COMMON_TAG,TAG+"onDetach");
    }

}
