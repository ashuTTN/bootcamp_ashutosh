package com.example.storageq2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    Button write,read;
    EditText input;
    TextView result;
    String file = "myfile";
    String fileContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write = findViewById(R.id.write);
        read = findViewById(R.id.read);
        input= findViewById(R.id.input);
        result = findViewById(R.id.result);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileContents = input.getText().toString();

                try{
                    FileOutputStream fOut = openFileOutput(file,MODE_PRIVATE);
                    fOut.write(fileContents.getBytes());
                    fOut.close();
                    File fileDir = new File(getFilesDir(),file);
                    Toast.makeText(getBaseContext(),"File Saved at" + fileDir,Toast.LENGTH_LONG).show();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fIn = openFileInput(file);
                    int c;
                    String temp = "";

                    while((c = fIn.read())!= -1){
                        temp = temp + Character.toString((char)c);
                    }
                    result.setText(temp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
}
