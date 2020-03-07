package com.example.fragmentpart4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DetailFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        View view1 = view.findViewById(R.id.title);
        View view2 = view.findViewById(R.id.desc);

        Bundle bundle = getArguments();
        int position = bundle.getInt("itemPosition");
        Log.d(TAG, "onCreateView: "+position);
        String Title = getTitle(position);
        String Desc = getDesc(position);

        ((TextView)view1).setText(Title);
        ((TextView)view2).setText(Desc);

        return view;
    }

    private String getDesc(int position) {

        ArrayList<String> desc = new ArrayList<>();
        desc.add("Apple Inc. is an American multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, computer software, and online services. It is considered one of the Big Four technology companies, alongside Amazon, Google, and Facebook.");
        desc.add("Samsung is a South Korean multinational conglomerate headquartered in Samsung Town, Seoul. It comprises numerous affiliated businesses, most of them united under the Samsung brand, and is the largest South Korean chaebol.");
        desc.add("One Plus Technology Co., Ltd., commonly referred to as OnePlus, is a Chinese smartphone manufacturer, which is based in Shenzhen, Guangdong. It was founded by Pete Lau and Carl Pei in December 2013. The company officially serves 34 countries and regions around the world as of July 2018.");
        desc.add("Nokia Corporation is a Finnish multinational telecommunications, information technology, and consumer electronics company, founded in 1865. Nokia's headquarters are in Espoo, in the greater Helsinki metropolitan area.");

        String Desc = desc.get(position);

        return Desc;
    }

    private String getTitle(int position) {

        ArrayList<String> title = new ArrayList<>();
        title.add("Apple");
        title.add("Samsung");
        title.add("OnePlus");
        title.add("Nokia");

        String Title = title.get(position);

        return Title;
    }


}