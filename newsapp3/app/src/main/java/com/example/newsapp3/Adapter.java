package com.example.newsapp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.newsapp3.ModelClass.LARGE_LAYOUT;
import static com.example.newsapp3.ModelClass.SMALL_LAYOUT;

class Adapter extends RecyclerView.Adapter {
    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case LARGE_LAYOUT:
                View large_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.large_layout,parent,false);
                return new LargeLayout(large_layout);
            case SMALL_LAYOUT:
                View small_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_layout,parent,false);
                return new SmallLayout(small_layout);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (modelClassList.get(position).getViewType()){
            case LARGE_LAYOUT:
                String largeText = modelClassList.get(position).getLargeText();
                int largeImage = modelClassList.get(position).getLargeImage();
                String largeTitle = modelClassList.get(position).getLargeTitle();
                ((LargeLayout)holder).setData(largeImage,largeText,largeTitle);
                break;
            case SMALL_LAYOUT:
                String smallText = modelClassList.get(position).getSmallText();
                int smallImage = modelClassList.get(position).getSmallImage();
                String smallTitle = modelClassList.get(position).getSmallTitle();
                ((SmallLayout)holder).setData(smallImage,smallText,smallTitle);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (modelClassList.get(position).getViewType()){
            case 0:
                return LARGE_LAYOUT;
            case 1:
                return SMALL_LAYOUT;
            default:
                return -1;
        }
    }



    private class LargeLayout extends RecyclerView.ViewHolder {
        ImageView large_image1;
        TextView large_text1;
        TextView large_title1;
        public LargeLayout(View large_layout) {
            super(large_layout);
            large_image1 = large_layout.findViewById(R.id.largeImage);
            large_text1 = large_layout.findViewById(R.id.largeText);
            large_title1 = large_layout.findViewById(R.id.largeTitle);
        }
        private void setData(int large_image , String large_text,String large_title){
            large_text1.setText(large_text);
            large_image1.setImageResource(large_image);
            large_title1.setText(large_title);
        }

    }

    private class SmallLayout extends RecyclerView.ViewHolder {
        private ImageView small_image1;
        private TextView small_text1;
        private TextView small_title1;

        public SmallLayout(View small_layout) {
            super(small_layout);
            small_image1 = small_layout.findViewById(R.id.smallImage);
            small_text1 = small_layout.findViewById(R.id.smallText);
            small_title1 = small_layout.findViewById(R.id.smallTitle);
        }

        private void setData(int small_image, String small_text,String small_title){
            small_image1.setImageResource(small_image);
            small_text1.setText(small_text);
            small_title1.setText(small_title);
        }
    }
}
