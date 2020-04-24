package com.example.newsapp3;


class ModelClass {
    static final int LARGE_LAYOUT = 0;
    static final int SMALL_LAYOUT = 1;
    private int viewType;
    private String largeTitle;
    private String largeText;
    private String smallText;
    private String smallTitle;
    private int largeImage;
    private int smallImage;

    int getViewType() {
        return viewType;
    }


    ModelClass(int viewType, String largeText, int largeImage, String largeTitle) {
        this.viewType = viewType;
        this.largeText = largeText;
        this.largeImage = largeImage;
        this.largeTitle = largeTitle;
    }

    ModelClass(int viewType, int smallImage, String smallText, String smallTitle) {
        this.viewType = viewType;
        this.smallText = smallText;
        this.smallImage = smallImage;
        this.smallTitle = smallTitle;
    }

    String getLargeText() {
        return largeText;
    }

    String getSmallText() {
        return smallText;
    }

    int getLargeImage() {
        return largeImage;
    }

    int getSmallImage() {
        return smallImage;
    }

    String getLargeTitle() {
        return largeTitle;
    }
    String getSmallTitle(){
        return smallTitle;
    }
}
