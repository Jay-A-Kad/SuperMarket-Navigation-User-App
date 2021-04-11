package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Store extends AppCompatActivity {

    BottomNavigationView bottomnavigationbar;
    ImageButton btnGroccery, btnClothing, btnElectronic;
    ImageButton btnStoreToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

//        hooks for all buttons
        btnStoreToCart = findViewById(R.id.storeToCart);
        btnGroccery  = findViewById(R.id.store1);
        btnClothing =  findViewById(R.id.store2);
        btnElectronic =  findViewById(R.id.store3);


//      item listeners

            btnStoreToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("alert","button is working");
                    Intent hockey = new Intent(Store.this,cartFunction.class);
                    startActivity(hockey);
                    finish();
                }
            });


                btnGroccery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent grocIntent = new Intent(Store.this, grocceryTab.class);
                        startActivity(grocIntent);
                    }
                });

            btnClothing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent clothIntent = new Intent(Store.this, clothingTab.class);
                    startActivity(clothIntent);
                }
            });


        btnElectronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent electIntent = new Intent(Store.this,electronicTab.class);
                startActivity(electIntent);
            }
        });


//        hooks for bottom  nav tab
        bottomnavigationbar = findViewById(R.id.bottom_nav_menu);
        bottomnavigationbar.setSelectedItemId(R.id.bottom_nav_store);
        Menu menu = bottomnavigationbar.getMenu();
        MenuItem menuItem= menu.getItem(1);
        menuItem.setChecked(true);


        bottomnavigationbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_nav_home:
                        Intent intent0 =new Intent(Store.this,Home.class);
                        startActivity(intent0);
                        break;

                    case R.id.bottom_nav_store:
                        break;

                    case R.id.bottom_nav_profile:
                        Intent intent2 =new Intent(Store.this,UserProfile.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }

        });


    }



}