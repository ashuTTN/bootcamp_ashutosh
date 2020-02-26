package com.example.androidexercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        e1 = (EditText)findViewById(R.id.usernameText);
        e2 = (EditText)findViewById(R.id.emailText);
        e3 = (EditText)findViewById(R.id.passwordText);
        e4 = (EditText)findViewById(R.id.phoneText);
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public boolean isValidPhone(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }
    public void doSomething(View view) {
        String username = e1.getText().toString();
        String email = e2.getText().toString();
        String password = e3.getText().toString();
        String phone = e4.getText().toString();
        int flag = 0;
        if(username.length() == 0){
            e1.setError("Field can't be Blank");
            flag = 1;
        }
        if(password.length() == 0){
            e3.setError("Field can't be Blank");
            flag = 1;
        }
        if(!isValidPhone(phone) ){
            e4.setError("Invalid Phone");
            flag = 1;
        }
        if(!isValidEmail(email)){
            e2.setError("Invalid Email");
            flag =1;
        }
        if (flag == 0){
            Intent I1 = new Intent(this,Second.class);
            I1.putExtra("user",username);
            I1.putExtra("email",email);
            I1.putExtra("password",password);
            I1.putExtra("phone",phone);
            startActivity(I1);
        }

    }
}
