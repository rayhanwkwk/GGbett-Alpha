package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword;
    Button btnRegister;
    private DatabaseReference database;
    TextView textReg;



    @SuppressLint("MissingInflated")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        textReg=findViewById(R.id.txtSGNlog);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v->{
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        });
        textReg.setOnClickListener(v->{
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        });

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ggbettdatabase-default-rtdb.firebaseio.com/");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Data harus Diisi!" , Toast.LENGTH_SHORT).show();
                }else{
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(),"Register Berhasil" , Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(register);
                }
            }
        });
    }
}