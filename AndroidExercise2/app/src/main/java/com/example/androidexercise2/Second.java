package com.example.androidexercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends AppCompatActivity {
    private int CAMERA_PERMISSION_CODE = 1;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    EditText inputURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txt1 = (TextView)findViewById(R.id.userResult);
        txt2 = (TextView)findViewById(R.id.emailResult);
        txt3 = (TextView)findViewById(R.id.passResult);
        txt4 = (TextView)findViewById(R.id.phoneResult);
        inputURL = (EditText)findViewById(R.id.editText);

        Bundle b1 = getIntent().getExtras();
        String s1 = b1.getString("user");
        String s2 = b1.getString("email");
        String s3 = b1.getString("password");
        String s4 = b1.getString("phone");


        txt1.setText(s1);
        txt2.setText(s2);
        txt3.setText(s3);
        txt4.setText(s4);
    }
    private void requestCameraPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }
    }

    public void openWeb(View view) {
        String url = inputURL.getText().toString();
        switch (view.getId()){
            case (R.id.open_url_button):
                if(url.indexOf("https://" ) < 0){
                    url = "https://" + url;
                }
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i2);
                break;
            case (R.id.check_permission_button):
                if(ContextCompat.checkSelfPermission(Second.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission already granted",Toast.LENGTH_LONG).show();
                }
                else{
                    requestCameraPermission();
                }
        }

    }

}
