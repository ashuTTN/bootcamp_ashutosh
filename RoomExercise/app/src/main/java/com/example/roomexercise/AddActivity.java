package com.example.roomexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText nameEditText,addressEditText,mobileEditText;
    private String name,address,mobile;
    private Button addButton;
    int id;
    boolean IS_UPDATE = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initializeFields();
    }

    private void initializeFields() {
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        addButton = findViewById(R.id.addEmp);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();

            }
        });
        getIntentData();

    }

    private void getIntentData() {
        if(getIntent().hasExtra("record"))
        {
            IS_UPDATE = true;
            addButton.setText("UPDATE");
            EmployeeEntity employeeModel =(EmployeeEntity) getIntent().getSerializableExtra("record");
            nameEditText.setText(employeeModel.getName());
            addressEditText.setText(employeeModel.getAddress());
            mobileEditText.setText(employeeModel.getMobile());
            id = employeeModel.getId();
        }
        else{
            IS_UPDATE = false;
            addButton.setText("ADD");
        }
    }

    private void saveEmployee() {
        name = nameEditText.getText().toString();
        address = addressEditText.getText().toString();
        mobile = mobileEditText.getText().toString();

        if (IS_UPDATE ==false) {
            SaveEmployeeRecordsTask saveEmployeeRecordsTask = new SaveEmployeeRecordsTask();
            saveEmployeeRecordsTask.execute();
        }
        else {
            UpdateEmployeeRecordsTask updateEmployeeRecordsTask = new UpdateEmployeeRecordsTask();
            updateEmployeeRecordsTask.execute();
        }

    }

    class SaveEmployeeRecordsTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setName(name);
            employeeEntity.setAddress(address);
            employeeEntity.setMobile(mobile);

            EmployeeDatabase.getInstance(getApplicationContext()).employeeDao()
                    .insert(employeeEntity);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            startActivity(new Intent(AddActivity.this,MainActivity.class));
            finish();
            Toast.makeText(AddActivity.this,"Record Saved..",Toast.LENGTH_LONG).show();
        }
    }

    class UpdateEmployeeRecordsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            EmployeeEntity employeeModel = new EmployeeEntity();
            employeeModel.setName(name);
            employeeModel.setAddress(address);
            employeeModel.setMobile(mobile);
            employeeModel.setId(id);


            EmployeeDatabase.getInstance(getApplicationContext())
                    .employeeDao().update(employeeModel);

            return null;
        }




        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(getApplicationContext(), "Record Updated..", Toast.LENGTH_LONG).show();
        }
    }

}
