package com.example.fcmassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private TextView text1, text2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        imageView  = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String data = intent.getStringExtra("data");
        String URL = intent.getStringExtra("URL");

        text1.setText(title);
        text2.setText(data);

        Glide.with(this).load(URL)
                .fitCenter()
                .into(imageView);

    }
}
