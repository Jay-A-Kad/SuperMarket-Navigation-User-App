package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class grocceryTab extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    modelGroccery newGroccery;
    private FirebaseDatabase fDatabase;
    ProgressBar itemBar;
    private DatabaseReference dRef;
    private DatabaseReference root = db.getReference().child("groccery");
    private myAdapterGroccery groccery_Adapter;
    private ArrayList<modelGroccery> glist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groccery_tab);

//        data base hooks

        recyclerView = findViewById(R.id.rv_Groccery);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemBar = findViewById(R.id.progressBar2);

        glist = new ArrayList<>();
        groccery_Adapter = new myAdapterGroccery(this ,glist);
        recyclerView.setAdapter(groccery_Adapter);
        db.getReference().child("p_id");


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    modelGroccery model = dataSnapshot1.getValue(modelGroccery.class);
                    glist.add(model);
                }


                groccery_Adapter.notifyDataSetChanged();
                itemBar.setVisibility(View.INVISIBLE);





            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void back(View v) {
        Intent backIntent = new Intent(grocceryTab.this,Store.class);
        startActivity(backIntent);
    }





}