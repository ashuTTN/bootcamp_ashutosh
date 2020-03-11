package com.example.newsapp3;

import java.util.List;

class ModelClass {
    public static final int LARGE_LAYOUT = 0;
    public static final int SMALL_LAYOUT = 1;
    private int viewType;
    private String largeTitle;
    private String largeText;
    private String smallText;
    private String smallTitle;
    private int largeImage;
    private int smallImage;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public ModelClass(int viewType, String largeText, int largeImage,String largeTitle) {
        this.viewType = viewType;
        this.largeText = largeText;
        this.largeImage = largeImage;
        this.largeTitle = largeTitle;
    }

    public ModelClass(int viewType,int smallImage,String smallText, String smallTitle) {
        this.viewType = viewType;
        this.smallText = smallText;
        this.smallImage = smallImage;
        this.smallTitle = smallTitle;
    }

    public String getLargeText() {
        return largeText;
    }

    public String getSmallText() {
        return smallText;
    }

    public int getLargeImage() {
        return largeImage;
    }

    public int getSmallImage() {
        return smallImage;
    }

    public String getLargeTitle() {
        return largeTitle;
    }
    public String getSmallTitle(){
        return smallTitle;
    }
}
