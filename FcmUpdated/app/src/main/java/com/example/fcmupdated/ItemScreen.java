package com.example.fcmupdated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);

        TextView item_price = findViewById(R.id.itme_price);
        ImageView imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String url = intent.getStringExtra("imgURL");

        item_price.setText(price);
        Glide.with(this).load(url)
                .fitCenter()
                .into(imageView);
    }
}
