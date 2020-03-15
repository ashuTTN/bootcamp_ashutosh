package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText num1,num2;
    ProgressDialog mProgressDialog;
    Button btn,btn1;
    String strUrl = "https://images.pexels.com/photos/1236701/pexels-photo-1236701.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260";
    ImageView imageV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn1 = findViewById(R.id.button1);
        imageV = findViewById(R.id.imageV);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageDownloader(strUrl).execute();
            }
        });



    }


    // DownloadImage AsyncTask
    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        String strUrl1;
        public ImageDownloader(String strUrl) {
            this.strUrl1 = strUrl;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Download Image ");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
        public boolean isConnected() {
            Context context = getApplicationContext();
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);

            if ( connectivity != null) {

                NetworkInfo info = connectivity.getActiveNetworkInfo();

                if (info != null) {

                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

            return false;
        }
        @Override
        protected Bitmap doInBackground(String... URL) {
            if(isConnected()){
                return fetchURL();
            }
            else{
                while(isConnected() == false){
                    mProgressDialog.setTitle("PAUSED");
                    mProgressDialog.show();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(isConnected()){
                    mProgressDialog.dismiss();
                    return fetchURL();
                }
                return null;
            }

        }

        protected Bitmap fetchURL(){
            if(isConnected()){
                String imageURL = strUrl1;

                Bitmap bitmap = null;
                try {
                    // Download Image from URL
                    InputStream input = new java.net.URL(imageURL).openStream();
                    // Decode Bitmap
                    bitmap = BitmapFactory.decodeStream(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            if(!isConnected()){
                Toast.makeText(MainActivity.this, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
            // Set the bitmap into ImageView
            imageV.setImageBitmap(result);
            // Close progressdialog
            mProgressDialog.dismiss();
        }
    }
}