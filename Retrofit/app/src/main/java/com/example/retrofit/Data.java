package com.example.retrofit;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    @SerializedName("posts")
    private List<RetroModel> retroDataList;

    public List<RetroModel> getRetroDataList() {
        return retroDataList;
    }

    public void setRetroDataList(List<RetroModel> retroDataList) {
        this.retroDataList = retroDataList;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "Data{" +
                        "posts = '" + retroDataList + '\'' +
                        "}";
    }
}
