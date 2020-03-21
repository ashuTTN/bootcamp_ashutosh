package com.example.inteegrationwithwebservices;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStorageState;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_LENGTH = 7;

    Button httpButton,glideButton ;
    ImageView imageV ;
    ProgressBar progressBar;

//    public String strUrl = "https://image.freepik.com/free-photo/image-human-brain_99433-298.jpg";
    public String strUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";
    public String strUrl1 = "https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpButton = findViewById(R.id.httpButton);
        glideButton = findViewById(R.id.glideButton);
        imageV = findViewById(R.id.imageV);
        progressBar = findViewById(R.id.progressBar);

        httpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageDownloader imageDownloader = new ImageDownloader();
                imageDownloader.execute(strUrl);
            }
        });

        glideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this).load(strUrl1).into(imageV);
            }
        });

    }
    private class ImageDownloader extends AsyncTask<String, Integer, String>{
        private String PATH = getExternalStorageDirectory().getAbsolutePath() + "/" + randomString()+".jpg";
        @Override
        protected String doInBackground(String... strUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpsURLConnection connection = null;

            try{
                URL url = new URL(strUrl[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                int fileLength = connection.getContentLength();
                input = connection.getInputStream();
//                path = getExternalStorageDirectory().getAbsolutePath() + "/" + randomString()+".jpg";
                output = new FileOutputStream(PATH);
                byte data[] = new byte[4096];
                long total = 0 ;
                int count ;
                while (((count = input.read(data)) != -1)){
                    total+=count;
                    publishProgress((int)total*100 / fileLength);
                    output.write(data,0,count);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                    output.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
//            path = getExternalStorageDirectory().getAbsolutePath() + "/" + randomString()+".jpg";
            Bitmap bitmap = BitmapFactory.decodeFile(PATH);
            imageV.setImageBitmap(bitmap);
        }
    }
    public static String randomString() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
