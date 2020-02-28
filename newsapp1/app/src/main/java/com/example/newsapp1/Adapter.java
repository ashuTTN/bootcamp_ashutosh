package com.example.newsapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.newsapp1.ModelClass.IMAGE_ONLY_LAYOUT;
import static com.example.newsapp1.ModelClass.TEXT_ONLY_LAYOUT;
import static com.example.newsapp1.ModelClass.TEXT_WITH_SMALL_IMAGE_LAYOUT;

public class Adapter extends RecyclerView.Adapter {

    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) { //5.2 insert constructor
        this.modelClassList = modelClassList;
    }


    @Override
    public int getItemViewType(int position) {
        switch (modelClassList.get(position).getViewType()){
            case 0:
                return TEXT_ONLY_LAYOUT;
            case 1:
                return IMAGE_ONLY_LAYOUT;
            case 2:
                return  TEXT_WITH_SMALL_IMAGE_LAYOUT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case TEXT_ONLY_LAYOUT:
                View text_only_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.textonly,parent,false);
                return new TextOnlyLayout(text_only_layout);
            case IMAGE_ONLY_LAYOUT:
                View image_only_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_only_layout,parent,false);
                return new ImageOnlyLayout(image_only_layout);
            case TEXT_WITH_SMALL_IMAGE_LAYOUT:
                View text_with_small_image_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_with_small_image,parent,false);
                return new TextWithSmallImage(text_with_small_image_layout);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (modelClassList.get(position).getViewType()){
            case TEXT_ONLY_LAYOUT:
                String textonylTitle = modelClassList.get(position).getTextonylTitle();
                String textonylContent = modelClassList.get(position).getTextonylContent();
                String textonlyTime = modelClassList.get(position).getTextonlyTime();
                ((TextOnlyLayout)holder).setData(textonylTitle,textonylContent,textonlyTime);

                break;
            case IMAGE_ONLY_LAYOUT:
                int resource = modelClassList.get(position).getResource();
                ((ImageOnlyLayout)holder).setData(resource);
                break;
            case TEXT_WITH_SMALL_IMAGE_LAYOUT:
                int resource1 = modelClassList.get(position).getResource1();
                String textsmalltitle = modelClassList.get(position).getText_with_small_image_title();
                String textsmalldesc = modelClassList.get(position).getText_with_small_image_content();
                String textsmalltime = modelClassList.get(position).getText_with_small_time();
                int textsmallimage = modelClassList.get(position).getResource1();
                ((TextWithSmallImage)holder).setData(textsmalldesc,textsmalltitle,textsmalltime,textsmallimage);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class TextOnlyLayout extends RecyclerView.ViewHolder{
        private TextView textonlytitle1;
        private TextView textonlydesc1;
        private TextView textonlytime1;
        public TextOnlyLayout(@NonNull View itemView) {
            super(itemView);
            textonlytitle1 = itemView.findViewById(R.id.textonlytitle);
            textonlydesc1 = itemView.findViewById(R.id.textonlydesc);
            textonlytime1 = itemView.findViewById(R.id.textonlytime);
        }
        private void setData(String title,String desc, String time){
            textonlytitle1.setText(title);
            textonlydesc1.setText(desc);
            textonlytime1.setText(time);
        }
    }

    class ImageOnlyLayout extends RecyclerView.ViewHolder{
        private ImageView imageonlyimage;
        public ImageOnlyLayout(@NonNull View itemView) {
            super(itemView);
            imageonlyimage = itemView.findViewById(R.id.imageonlyimage);
        }
        private void setData(int image){
            imageonlyimage.setImageResource(image);
        }
    }

    class TextWithSmallImage extends RecyclerView.ViewHolder{
        private ImageView textwithsmallimage;
        private TextView textwithsmalltitle;
        private TextView textwithsmalldesc;
        private TextView textwithsmalltime;
        public TextWithSmallImage(@NonNull View itemView) {
            super(itemView);
            textwithsmalldesc = itemView.findViewById(R.id.textwithsmalldesc);
            textwithsmallimage = itemView.findViewById(R.id.textwithsmallimage);
            textwithsmalltitle = itemView.findViewById(R.id.textwithsmalltitle);
            textwithsmalltime = itemView.findViewById(R.id.textwithsmalltime);
        }
        private void setData(String desc,String title,String time , int image){
            textwithsmalltime.setText(time);
            textwithsmalldesc.setText(desc);
            textwithsmallimage.setImageResource(image);
            textwithsmalltitle.setText(title);
        }
    }

}
