package com.ehb.masar.goldapplicatie.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ehb.masar.goldapplicatie.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by masar on 25/12/2018.
 */

public class SignUpActivity  extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText editEmail;
    private EditText editPassword ;
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        Button btnSignUp =  findViewById(R.id.btnSignUp);

        progressDialog = new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });}


    public void registerUser(){
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this , "Please enter your Email",Toast.LENGTH_LONG).show();

        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this , "Please enter your password",Toast.LENGTH_LONG).show();

        }
            progressDialog.setMessage("Registration new User....");
            progressDialog.show();


            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this , "User registration is success full", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this , SignInActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(SignUpActivity.this , "Error registrations " , Toast.LENGTH_LONG).show();
                            }

                            progressDialog.dismiss();
                        }
                    });





        }




    }

