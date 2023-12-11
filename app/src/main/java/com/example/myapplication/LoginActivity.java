package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    Button btnlogin;
    private DatabaseReference database;
    TextView textLog, txtSGNreg, txtSGNlupapw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        textLog = findViewById(R.id.txtSGNreg);
        //txtSGNreg = findViewById(R.id.txtSGNreg);

        textLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
            }
        });


        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnlogin=findViewById(R.id.btnlogin);
        textLog=findViewById(R.id.txtSGNreg);


        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("users");
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                }else {
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(email).exists()){
                                if(snapshot.child(email).child("password").getValue(String.class).equals(password)){
                                    Toast.makeText(getApplicationContext(),"Login berhasil",Toast.LENGTH_SHORT).show();
                                    Intent masuk = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(masuk);
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}