package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private HttpsURLConnection httpURLConnection = null;
    private URL url;
    private BufferedReader reader = null;
    private List<RetroModel> retroModelList = new ArrayList<>();

    String BASE_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button retroButton = findViewById(R.id.retroButton);
        Button httpsButton = findViewById(R.id.https);

        retroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Data> call = RetrofitInstance.getDataService().getAllPosts();
                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        Data data = response.body();
                        List<RetroModel> retroData = data.getRetroDataList();
                        retroModelList.addAll(retroData);
                        generateDataList(retroModelList);
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });
            }
        });

        httpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataDownload().execute(BASE_URL);
            }
        });
    }
    private void generateDataList(List postList) {
        MyAdapter adapter = new MyAdapter(this,postList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    //Downloading task with HTTPS
    public class DataDownload extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = reader.readLine())!=null){

                    stringBuilder.append(line);

                }
                return stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            httpURLConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            Data data = gson.fromJson(result, Data.class);
            adapter = new MyAdapter(MainActivity.this,data.getRetroDataList());
            recyclerView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
        }
    }
}
