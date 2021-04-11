package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class cartFunction extends AppCompatActivity {

    Button NavCart;
    private RecyclerView recyclerViewCart;
    private FirebaseAuth fAuth;
    private FirebaseDatabase fDb = FirebaseDatabase.getInstance();
    private myAdapterCartFunction cart_fun_Adapter;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String uId;
    ProgressBar cartbar;
    TextView totalcart;
    Integer childrenCount;
    private Integer total=0;
    private ArrayList<modelCartFunction> funlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_function);

        cartbar = findViewById(R.id.cartBar);
        NavCart = findViewById(R.id.navigateItemsFromCart);

//        on nav click

        NavCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      Intent i1 = new Intent(cartFunction.this,Home.class);
                      startActivity(i1);

            }
        });


        //               data base hooks
        totalcart = (TextView)findViewById(R.id.txtTotalPrice);
        recyclerViewCart = findViewById(R.id.rv_cartRecylerFun);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        funlist = new ArrayList<>();
        cart_fun_Adapter = new myAdapterCartFunction(this,funlist);
        recyclerViewCart.setAdapter( cart_fun_Adapter);
        user = fAuth.getInstance().getCurrentUser();
        uId = user.getUid();

        DatabaseReference rootCart = fDb.getReference().child("user_orders").child(uId);
        rootCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    modelCartFunction cartModel = dataSnapshot1.getValue(modelCartFunction.class);
                    funlist.add(cartModel);


                }

                cart_fun_Adapter.notifyDataSetChanged();

                DatabaseReference totalitems = FirebaseDatabase.getInstance().getReference().child("user_orders").child(uId);
                totalitems.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            total = (int)snapshot.getChildrenCount();
                            totalcart.setText(Integer.toString(total));

                            cartbar.setVisibility(View.INVISIBLE);
                        }
                        else {
                            totalcart.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public void back(View v) {
        Intent backIntent = new Intent(cartFunction.this,Store.class);
        startActivity(backIntent);
    };











}