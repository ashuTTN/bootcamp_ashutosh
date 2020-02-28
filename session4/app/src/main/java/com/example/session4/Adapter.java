package com.example.session4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private List<ModelClass> modelClassList;    //5.1

    public Adapter(List<ModelClass> modelClassList) { //5.2 insert constructor
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    //5. implement the methods
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false); //5.3 inflate your layout
        return new Viewholder(view); //5.4
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String resource = modelClassList.get(position).getText(); //5.5
        holder.setData(resource);
    }

    @Override
    public int getItemCount() {      //inflate the number of items.
        return modelClassList.size(); //5.6
    }    // 4.1

    class Viewholder extends RecyclerView.ViewHolder{ //4.2 create a viewholder class and create constructor

        private TextView textView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView); //4.3 access your views defined in list_layout

        }

        private void setData(String text){    //4.4 create a method to set data
            textView.setText(text);
        }
    }

}
