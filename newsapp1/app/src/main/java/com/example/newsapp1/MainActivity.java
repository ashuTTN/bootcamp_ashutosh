package com.example.newsapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static com.example.newsapp1.ModelClass.IMAGE_ONLY_LAYOUT;
import static com.example.newsapp1.ModelClass.TEXT_ONLY_LAYOUT;
import static com.example.newsapp1.ModelClass.TEXT_WITH_SMALL_IMAGE_LAYOUT;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(IMAGE_ONLY_LAYOUT,R.drawable.bannerimage));
        modelClassList.add(new ModelClass(TEXT_ONLY_LAYOUT,"THE TIMES OF INDIA","Delhi violence: Sonia seeks Amit Shah's resignation; BJP calls it \'dirty politics\'","Headlines- 10 minutes ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smalliimage1,"FIRSTPOST","With no trade deal signed, Donald Trump-Narendra Modi jamboree was about two men, not two countries","Headlines-5 Hours ago"));
        modelClassList.add(new ModelClass(TEXT_WITH_SMALL_IMAGE_LAYOUT,R.drawable.smallimage2,"THE TIMES OF INDIA","Delhi violence Liquor shops closed in Noida today","Local - 10 Hours ago"));



        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
