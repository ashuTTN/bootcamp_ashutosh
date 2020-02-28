package com.example.session4;

import androidx.recyclerview.widget.RecyclerView;

public class ModelClass{
    private String text; // 3.1 create your variables

    public ModelClass(String text) { //3.2 insert constructor
        this.text = text;
    }

    public String getText() { // 3.3 set getter
        return text;
    }

}
