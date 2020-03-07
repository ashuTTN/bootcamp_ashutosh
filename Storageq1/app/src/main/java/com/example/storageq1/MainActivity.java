package com.example.storageq1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submitButton = findViewById(R.id.submit);
        Button retrieve = findViewById(R.id.retrieve);
        final String[] text = new String[1];
        final EditText editText = findViewById(R.id.editText);
        final TextView result = findViewById(R.id.result);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("file", MainActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                switch (v.getId()){
                    case(R.id.submit):
                        editor.putString("edit text",editText.getText().toString());
                        editor.apply();
                        break;
                    case(R.id.retrieve):
                        text[0] = sharedPreferences.getString("edit text",null);
                        result.setText(text[0]);
                        break;
                }


            }
        };
        submitButton.setOnClickListener(listener);
        retrieve.setOnClickListener(listener);

    }
}
