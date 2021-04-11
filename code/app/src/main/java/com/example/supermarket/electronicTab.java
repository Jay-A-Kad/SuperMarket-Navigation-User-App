package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class electronicTab extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("electronic");
    private myAdapterElectronic electronic_Adapter;
    private ArrayList<modelElectronic> elist;
    ProgressBar itemBar3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_tab);

        //               data base hooks

        recyclerView = findViewById(R.id.rv_Electronic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemBar3 = findViewById(R.id.progressBar4);

        elist = new ArrayList<>();
        electronic_Adapter = new myAdapterElectronic(this ,elist);
        recyclerView.setAdapter(electronic_Adapter);
        db.getReference().child("p_id");

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    modelElectronic model = dataSnapshot1.getValue(modelElectronic.class);
                    elist.add(model);
                }


                electronic_Adapter.notifyDataSetChanged();
                itemBar3.setVisibility(View.INVISIBLE);

    }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void back(View v) {
        Intent backIntent = new Intent(electronicTab.this,Store.class);
        startActivity(backIntent);
    };
}