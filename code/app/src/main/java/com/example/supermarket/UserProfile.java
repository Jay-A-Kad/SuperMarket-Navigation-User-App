package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {

    TextView profileName, profileEmail, profilePhone;
    Button btnProfileLogout;
    BottomNavigationView bottomnavigationbar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        hooks for user profile
        profileName = findViewById(R.id.customerName1);
        profileEmail = findViewById(R.id.customerEmail1);
        profilePhone= findViewById(R.id.customerPhone1);

//        hooks for firebase and code
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference dRP = fstore.collection("app_users").document(userId);
        dRP.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                profileName.setText(value.getString("user_Fname"));
                profileEmail.setText(value.getString("user_Email"));
                profilePhone.setText(value.getString("user_Phone"));
            }
        });

//        hooks for button
        btnProfileLogout = findViewById(R.id.btnProfileLogout);

        //          hooks  for bottom nav
        bottomnavigationbar = findViewById(R.id.bottom_nav_menu);
        bottomnavigationbar.setSelectedItemId(R.id.bottom_nav_profile);
        Menu menu = bottomnavigationbar.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


//        code for profile bottom navigation bar

        bottomnavigationbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_home:
                        Intent intent0 = new Intent(UserProfile.this, Home.class);
                        startActivity(intent0);
                        break;

                    case R.id.bottom_nav_store:
                        Intent intent1 = new Intent(UserProfile.this, Store.class);
                        startActivity(intent1);
                        break;
                    case R.id.bottom_nav_profile:
                        break;
                }
                return false;
            }

        });

//        on item click action
        btnProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileLogout(view);
            }
        });
    }



//    user logout function

    public void profileLogout(View view) {
        FirebaseUser user = fAuth.getInstance().getCurrentUser();
        String uId = user.getUid();
        DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference().child("user_orders").child(uId);
        dbreference.getRef().removeValue();

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),UserLogin.class));
        finish();
        System.exit(0);
    }
}
