package com.example.databinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databinding.databinding.FragLayoutBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class frag extends Fragment {

    public frag(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        com.example.databinding.databinding.FragLayoutBinding fragLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.frag_layout, container, false);
       View output = fragLayoutBinding.getRoot();

       fragLayoutBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Adapter adapter = new Adapter(getContext(), userList());
       fragLayoutBinding.recycler.setAdapter(adapter);
       return output;


    }

    private List<User> userList() {
        List <String> names= Arrays.asList(getResources().getStringArray(R.array.namesList));
        List <String> email= Arrays.asList(getResources().getStringArray(R.array.EmailList));
        List <String> phoneNumber= Arrays.asList(getResources().getStringArray(R.array.numbers));

        List<User> Names = new ArrayList<>();
        int count = 0;

        for (String name:names){
            Names.add(new User(name,email.get(count),phoneNumber.get(count)));
            count++;
        }

        return Names;

    }

}
