package com.example.fragmentpart4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import static android.content.ContentValues.TAG;

public class ItemFragment extends ListFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,container,false);
        String[] listItems = {"Apple","Samsung","OnePlus","Nokia"};
        ListView list = view.findViewById(android.R.id.list);
        ArrayAdapter<String> listViewAdatpter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listItems);
        list.setAdapter(listViewAdatpter);
        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("itemPosition",position);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);


        ft.replace(R.id.detailFrame, detailFragment);
        ft.addToBackStack("descFrag");
        ft.commit();
    }
}