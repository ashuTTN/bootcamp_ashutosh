package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressDialog mProgressDialog;
    TextView percentText;
    Button btn, btn1;
    String strUrl = "https://images.pexels.com/photos/1236701/pexels-photo-1236701.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260";
    String strUrl1 = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg";
    ImageView imageV;
    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            Toast.makeText(this, "You have already granted the permission", Toast.LENGTH_SHORT).show();
        else{
            requestStoragePermissions();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn1 = findViewById(R.id.button1);
        imageV = findViewById(R.id.imageV);
        progressBar = findViewById(R.id.progrssBar);
        percentText = findViewById(R.id.percentText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()){
//                    ProgressDialog used for practice
//                    mProgressDialog = new ProgressDialog(MainActivity.this);
//                    mProgressDialog.setTitle("Downloading Image Using Async Task");
//                    mProgressDialog.setMessage("Please Wait... ");
//                    mProgressDialog.setIndeterminate(true);
//                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                    mProgressDialog.setCancelable(true);
                    final ImageDownloader imageDownloader = new ImageDownloader();
                    imageDownloader.execute(strUrl);
                }
                else {
                    Toast.makeText(MainActivity.this, "Internet not connected", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mProgressDialog = new ProgressDialog(MainActivity.this);
//                mProgressDialog.setTitle("Downloading Image Using Service");
//                mProgressDialog.setMessage("Please Wait... ");
//                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                mProgressDialog.setIndeterminate(false);
//                mProgressDialog.setCancelable(true);
//                mProgressDialog.show();
                Intent intent = new Intent(MainActivity.this,DownloaderService.class);
                intent.putExtra("url", strUrl1);
                intent.putExtra("receiver", new DownloadReceiver(new Handler()));
                startService(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "PERMiSSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestStoragePermissions() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE )){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }

    }




    private class DownloadReceiver extends ResultReceiver {
        public DownloadReceiver(Handler handler) {
            super(handler);
        }
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloaderService.UPDATE_PROGRESS) {
                int progress = resultData.getInt("progress"); //get the progress
//                mProgressDialog.setProgress(progress);
                progressBar.setProgress(progress);
                percentText.setText(progress +"%");
                if (progress == 100) {
                    imageV.setImageBitmap(BitmapFactory.decodeFile(getExternalStorageDirectory().getAbsolutePath() + "/" + "myImage1.jpg"));
//                    mProgressDialog.dismiss();
                }
            }
        }
    }

    public boolean isConnected() {
        Context context = getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    // DownloadImage AsyncTask
    private class ImageDownloader extends AsyncTask<String, Integer, String> {
        private Context context;
        Bitmap bitmap;
        private String path;

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpsURLConnection connection = null;

            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                int fileLength = connection.getContentLength();
                input = connection.getInputStream();
                path = getExternalStorageDirectory().getAbsolutePath() + "/" + "myImage.jpg";
                output = new FileOutputStream(path);
                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while (((count = input.read(data)) != -1)) {
                    Thread.sleep(10);  // To slow down the process. -- if internet is fast the download gets completed rapidly.
                    while (!isConnected()) {  // TO PAUSE THE DOWNLOAD IF INTERNET DISCONNECTED
                        //Do nothing...
                    } // download resumes if internet reconnects
                    total += count;
                    publishProgress((int) total * 100 / fileLength);
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
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
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.setMax(100);
//            mProgressDialog.setProgress(values[0]);
            percentText.setText(values[0]+"%");
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            if (isConnected()) {
                path = getExternalStorageDirectory().getAbsolutePath() + "/" + "myImage.jpg";
//                mProgressDialog.dismiss();
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                imageV.setImageBitmap(bitmap);
            }

        }
    }
}
