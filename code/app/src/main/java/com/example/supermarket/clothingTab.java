package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class clothingTab extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("clothing");
    private myAdapterClothing clothing_Adapter;
    private ArrayList<modelClothing> clist;
    ProgressBar itemBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_tab);

//               data base hooks

        recyclerView = findViewById(R.id.rv_Clothing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemBar1 = findViewById(R.id.progressBar3);

        clist = new ArrayList<>();
        clothing_Adapter = new myAdapterClothing(this ,clist);
        recyclerView.setAdapter(clothing_Adapter);
        db.getReference().child("p_id");

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    modelClothing model = dataSnapshot1.getValue(modelClothing.class);
                    clist.add(model);
                }


                clothing_Adapter.notifyDataSetChanged();
                itemBar1.setVisibility(View.INVISIBLE);
                long maxID = (dataSnapshot.getChildrenCount());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public void back(View v) {
        Intent backIntent = new Intent(clothingTab.this,Store.class);
        startActivity(backIntent);
    };
}