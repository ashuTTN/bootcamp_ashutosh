package com.example.roomexercise;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class ViewHolder extends RecyclerView.ViewHolder {
    TextView nameTextView,addressTextView,mobileTextView,openPoPMenu;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.name);
        addressTextView = itemView.findViewById(R.id.address);
        mobileTextView = itemView.findViewById(R.id.mobile);
        openPoPMenu = itemView.findViewById(R.id.textViewOptions);
    }
}
