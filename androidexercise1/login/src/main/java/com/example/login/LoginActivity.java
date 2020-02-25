package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



//        button_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = username.getText().toString();
//                String passwd = password.getText().toString();
//
//                if(TextUtils.isEmpty(user)){
//                    Toast.makeText(LoginActivity.this,"enter email",Toast.LENGTH_LONG).show();
//
//                }
//                if(TextUtils.isEmpty(passwd)){
//                    Toast.makeText(LoginActivity.this,"enter password",Toast.LENGTH_LONG).show();
//
//                }
//
//                else
//                    Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_LONG).show();
//
//
//            }
//        });
//
 }

    public void button_click(View view) {
        EditText userText = (EditText)findViewById(R.id.username);
        EditText passText = (EditText)findViewById(R.id.password);

        String user1 = "ashutosh@tothenew.com";
        String pass1 = "12345";
        String usr = userText.getText().toString();
        String pswd = passText.getText().toString();

        if(usr.isEmpty()){
            Toast.makeText(this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(pswd.isEmpty()){
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_LONG).show();
        }
        else if(userText.getText().toString().equals(user1) && passText.getText().toString().equals(pass1)){
            Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_LONG).show();
        }
    }
}
