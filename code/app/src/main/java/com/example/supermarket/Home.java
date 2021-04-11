package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.supermarket.HelperClassesHome.FeaturedAdapter;
import com.example.supermarket.HelperClassesHome.FeaturedHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomnavigationbar;
    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        hooks for top products recycler view
        
        featuredRecycler=findViewById(R.id.featuredRecycler);
        featuredRecycler();

//      hooks for bottom nav bar

        bottomnavigationbar = findViewById(R.id.bottom_nav_menu);
        bottomnavigationbar.setSelectedItemId(R.id.bottom_nav_home);
        Menu menu = bottomnavigationbar.getMenu();
        MenuItem menuItem= menu.getItem(0);
        menuItem.setChecked(true);


        bottomnavigationbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_nav_home:
                        break;

                    case R.id.bottom_nav_store:
                        Intent intent1 = new Intent(Home.this,Store.class);
                        startActivity(intent1);
                        break;

                    case R.id.bottom_nav_profile:
                        Intent intent2 = new Intent(Home.this,UserProfile.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }

        });
    }

//    code for featuredRecyler function

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featureditems = new ArrayList<>();

        featureditems.add(new FeaturedHelperClass(R.drawable.honeyimg,"Honey","Honey never expires."));
        featureditems.add(new FeaturedHelperClass(R.drawable.cucumberimg,"Cucumber","Cucumbers are 96% water."));
        featureditems.add(new FeaturedHelperClass(R.drawable.frozenfoodimg,"Frozen Food","Frozen vegetables are are even more nutritious than non-frozen veggies."));
        featureditems.add(new FeaturedHelperClass(R.drawable.microwaveimg,"Microwave","Cooking in the microwave is the best way to preserve the minerals and nutrients in food."));
        featureditems.add(new FeaturedHelperClass(R.drawable.shirtimg,"Shirts","It takes 700 gallons of water to make a cotton shirt. "));
        featureditems.add(new FeaturedHelperClass(R.drawable.computerimg,"Computer","The First Computer Mouse was Made of Wood."));
        featureditems.add(new FeaturedHelperClass(R.drawable.headphonesimg,"Headphones","The first modern headphones were produced in the kitchen."));
        featureditems.add(new FeaturedHelperClass(R.drawable.laptopsimg,"Laptop","Do you know more than 12,000 laptops go missing at US airports a week!"));

        adapter = new FeaturedAdapter(featureditems);
        featuredRecycler.setAdapter(adapter);
    }

    public void onClothing(View view) {
    }
}