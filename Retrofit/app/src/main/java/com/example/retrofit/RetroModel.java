package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RetroModel implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("message")
    private String message;

    @SerializedName("profileImage")
    private String profileImage;

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
