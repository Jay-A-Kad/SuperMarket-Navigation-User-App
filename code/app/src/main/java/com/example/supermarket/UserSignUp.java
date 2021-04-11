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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserSignUp extends AppCompatActivity {

    TextInputEditText userFullName, userEmail, userPhone, userPassword;
    Button btnSignUpToLogin, btnRegisterUser;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

//        hooks for button
        btnSignUpToLogin = findViewById(R.id.btnSignUpToLogin);
        btnRegisterUser = findViewById(R.id.btnUserRegister);

//        hooks for user register

        userFullName = findViewById(R.id.regFullName1);
        userEmail = findViewById(R.id.regEmail1);
        userPhone = findViewById(R.id.regPhoneNo1);
        userPassword = findViewById(R.id.regPassword1);

//        hooks for firebase and progress bar
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.userProgressBar);



//        on item click action

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regName = userFullName.getText().toString().trim();
                String regEmail = userEmail.getText().toString().trim();
                String regPhoneNo = userPhone.getText().toString().trim();
                String regPass = userPassword.getText().toString().trim();


//                error if fields empty
                if(TextUtils.isEmpty(regName)) {
                    userFullName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(regEmail)) {
                    userEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(regPhoneNo)) {
                    userPhone.setError("Phone Number is required");
                    return;
                }
                if(TextUtils.isEmpty(regPass)) {
                    userPassword.setError("password is required");
                    return;
                }
                if(regPass.length() <  6) {
                    userPassword.setError("password should be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

//                if current user
                if(fAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(),UserLogin.class));
                    finish();
                }



//                Register user on firebase

                fAuth.createUserWithEmailAndPassword(regEmail,regPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(UserSignUp.this,"user is created",Toast.LENGTH_SHORT).show();
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference dR = fstore.collection("app_users").document(userId);
                            Map<String,Object> app_users = new HashMap<>();
                            app_users.put("user_Fname",regName);
                            app_users.put("user_Email",regEmail);
                            app_users.put("user_Phone",regPhoneNo);
                            app_users.put("user_Password",regPass);
                            dR.set(app_users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Id","user profile is created for :"+userId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else {
                            Toast.makeText(UserSignUp.this, "Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });



        btnSignUpToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignUpToLoginIntent = new Intent(UserSignUp.this,UserLogin.class);
                startActivity(SignUpToLoginIntent);
            }
        });
    }
}