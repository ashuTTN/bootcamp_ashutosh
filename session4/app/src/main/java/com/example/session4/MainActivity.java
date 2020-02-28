package com.example.session4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<ModelClass> modelClass = new ArrayList<>();



        recyclerView = findViewById(R.id.recyclerView); // 1.1. access the recycler view in main
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); // 1.2. create a layoutmanager
        layoutManager.setOrientation(RecyclerView.VERTICAL); //1.3. set the orientation as vertical
        recyclerView.setLayoutManager(layoutManager);//1.4. set the layout manager on the recycler

        String[] data={"ashutosh","b","c","d","e","a","b","c","d","e","a","b","c","d","e","a","b","c","d","e"};

        for(int i = 0 ; i < data.length ; i++){
            modelClass.add(new ModelClass(data[i]));
        }

        Adapter adapter = new Adapter(modelClass);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
