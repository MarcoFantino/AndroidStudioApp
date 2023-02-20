package com.example.progettolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.progettolab.Obj.User;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegisterNow;
    private Button btnGuest;
    private TextView txtEError;
    private TextView txtPError;
    private TextView txtLoginError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        Model.setDataBaseHelper(MainActivity.this);


        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Registration.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEError.setVisibility(View.GONE);
                txtPError.setVisibility(View.GONE);
                txtLoginError.setVisibility(View.GONE);
                if(edtEmail.getText().toString().isEmpty()){
                    txtEError.setVisibility(View.VISIBLE);
                }else if(edtPassword.getText().toString().isEmpty()){
                    txtPError.setVisibility(View.VISIBLE);
                }else{
                    User u;
                    u = Model.getDataBaseHelper().logIn(edtEmail.getText().toString() , edtPassword.getText().toString());
                    if(u != null){
                        Model.setUser(u);
                        Intent intent = new Intent(MainActivity.this , Homepage.class);
                        startActivity(intent);
                    }else{
                        txtLoginError.setVisibility(View.VISIBLE);
                    }
                }
                edtEmail.setText("");
                edtPassword.setText("");
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.setUser(null);
                Intent intent = new Intent(MainActivity.this , Booking.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterNow = findViewById(R.id.btnRegister);
        btnGuest = findViewById(R.id.btnGuest);
        txtEError = findViewById(R.id.txtEError);
        txtPError = findViewById(R.id.txtPError);
        txtLoginError = findViewById(R.id.txtLoginError);
    }
}