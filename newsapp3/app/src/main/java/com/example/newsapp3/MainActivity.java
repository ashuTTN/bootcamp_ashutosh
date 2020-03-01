package com.example.newsapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static com.example.newsapp3.ModelClass.LARGE_LAYOUT;
import static com.example.newsapp3.ModelClass.SMALL_LAYOUT;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ModelClass> modelClassList;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        modelClassList = new ArrayList<>();

        modelClassList.add(new ModelClass(LARGE_LAYOUT,"Delhi violence sonia seeks Amit Shah's resignation; BJP calls it dirty politics",R.drawable.bannerimage,"THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smalliimage1,"With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","FIRSTPOST"));
        modelClassList.add(new ModelClass(SMALL_LAYOUT,R.drawable.smallimage2,"Delhi violence: Liquor shops closed in Noida today","THE TIMES OF INDIA"));




        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
