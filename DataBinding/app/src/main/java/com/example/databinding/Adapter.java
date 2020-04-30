package com.example.databinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.databinding.ItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<User> users= new ArrayList<>();

    Adapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_layout,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(itemLayoutBinding);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.itemLayoutBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding itemLayoutBinding;
        MyViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            itemLayoutBinding = itemView;
        }
    }
}
