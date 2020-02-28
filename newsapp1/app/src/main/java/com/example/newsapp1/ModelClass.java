package com.example.newsapp1;

public class ModelClass {
    public static final int TEXT_ONLY_LAYOUT = 0;
    public static final int IMAGE_ONLY_LAYOUT = 1;
    public static final int TEXT_WITH_SMALL_IMAGE_LAYOUT = 2;

    private int viewType;
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    //text only
    private String textonylTitle;
    private String textonylContent;
    private String textonlyTime;

    public ModelClass(int viewType,String textonylTitle,String textonylContent,String textonlyTime) {
        this.textonylTitle = textonylTitle;
        this.textonylContent = textonylContent;
        this.viewType = viewType;
        this.textonlyTime = textonlyTime;
    }

    public String getTextonylTitle() {
        return textonylTitle;
    }

    public void setTextonylTitle(String textonylTitle) {
        this.textonylTitle = textonylTitle;
    }

    public String getTextonylContent() {
        return textonylContent;
    }

    public void setTextonylContent(String textonylContent) {
        this.textonylContent = textonylContent;
    }

    public String getTextonlyTime() {
        return textonlyTime;
    }

    public void setTextonlyTime(String textonlyTime) {
        this.textonlyTime = textonlyTime;
    }

    //image only layout
    private int resource;

    public ModelClass(int viewType,int resource) {
        this.resource = resource;
        this.viewType = viewType;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    //text_with_small_image
    private int resource1;
    private String text_with_small_image_title;
    private String text_with_small_image_content;
    private String text_with_small_time;

    public ModelClass(int viewType,int resource1, String text_with_small_image_title, String text_with_small_image_content,String text_with_small_image_time) {
        this.resource1 = resource1;
        this.text_with_small_image_title = text_with_small_image_title;
        this.text_with_small_image_content = text_with_small_image_content;
        this.text_with_small_time = text_with_small_image_time;
        this.viewType = viewType;
    }

    public int getResource1() {
        return resource1;
    }

    public void setResource1(int resource1) {
        this.resource1 = resource1;
    }

    public String getText_with_small_image_title() {
        return text_with_small_image_title;
    }

    public void setText_with_small_image_title(String text_with_small_image_title) {
        this.text_with_small_image_title = text_with_small_image_title;
    }

    public String getText_with_small_image_content() {
        return text_with_small_image_content;
    }

    public void setText_with_small_image_content(String text_with_small_image_content) {
        this.text_with_small_image_content = text_with_small_image_content;
    }

    public String getText_with_small_time() {
        return text_with_small_time;
    }

    public void setText_with_small_time(String text_with_small_time) {
        this.text_with_small_time = text_with_small_time;
    }
}
