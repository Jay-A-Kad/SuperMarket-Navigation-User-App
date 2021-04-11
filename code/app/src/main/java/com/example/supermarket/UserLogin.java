package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity {

    TextInputEditText loginEmailId,loginPass;
    Button btnLogin, btnSignUp;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

//        hooks for button
         btnLogin=findViewById(R.id.btnLoginToDashboard);
         btnSignUp=findViewById(R.id.btnLoginToSignUp);

//         hooks for variable
        loginEmailId = findViewById(R.id.regEmail1);
        loginPass = findViewById(R.id.regPassword1);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.loginProgress);

//        item onCLick action

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regEmail = loginEmailId.getText().toString().trim();
                String regPass = loginPass.getText().toString().trim();

                if(TextUtils.isEmpty(regEmail)) {
                    loginEmailId.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(regPass)) {
                    loginPass.setError("password is required");
                    return;
                }
                if(regPass.length() <  6) {
                    loginPass.setError("password should be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

//                authorize user

                fAuth.signInWithEmailAndPassword(regEmail,regPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(UserLogin.this,"user is Logged in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else {
                            Toast.makeText(UserLogin.this, "Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("demo","signup activity begins..");
                Intent signUpIntent = new Intent(UserLogin.this,UserSignUp.class);
                startActivity(signUpIntent);

            }

        });


    }
}