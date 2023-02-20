package com.example.progettolab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Registration extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtNewEmail;
    private EditText edtNewPassword;
    private TextView txtUserNameError;
    private TextView txtEmailError;
    private TextView txtPassError;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initView();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUserNameError.setVisibility(View.GONE);
                txtEmailError.setVisibility(View.GONE);
                txtPassError.setVisibility(View.GONE);

                checkData();
            }
        });
    }

    private void initView(){
        edtUserName = findViewById(R.id.edtUserName);
        edtNewEmail = findViewById(R.id.edtNewEmail);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        txtUserNameError = findViewById(R.id.txtUserNameError);
        txtEmailError = findViewById(R.id.txtEmailError);
        txtPassError = findViewById(R.id.txtPassError);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void checkData(){
        if(edtUserName.getText().toString().isEmpty()){
            txtUserNameError.setVisibility(View.VISIBLE);
        }else if(edtNewEmail.getText().toString().isEmpty()){
            txtEmailError.setVisibility(View.VISIBLE);
        }else if(edtNewPassword.getText().toString().isEmpty()){
            txtPassError.setVisibility(View.VISIBLE);
        }else{
            boolean success = Model.getDataBaseHelper().registerUser(edtUserName.getText().toString() , edtNewEmail.getText().toString(), edtNewPassword.getText().toString());
            if (success){
                Toast.makeText(Registration.this, "Registration Confirmed", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(Registration.this, "Username or Email already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}