package com.example.supermarket;

import android.content.Context;
import java.util.Calendar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class myAdapterClothing extends RecyclerView.Adapter<myAdapterClothing.MyViewHolder>{


    public ArrayList<modelClothing> cList;
    Context context;
    final FirebaseDatabase fDatabase = FirebaseDatabase.getInstance();
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    public myAdapterClothing(Context context , ArrayList<modelClothing> cList) {
        this.cList = cList;
        this.context = context;
    }

    @NonNull
    @Override
    public myAdapterClothing.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.clothing_item , parent , false);
        return new myAdapterClothing.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapterClothing.MyViewHolder holder, int position) {
        modelClothing model = cList.get(position);
        holder.clothing_Name.setText(model.getP_name());
        holder.clothing_Available_Stock.setText(model.getP_qty());
        holder.clothing_Price.setText(model.getP_price());
        holder.clothing_id.setText(model.getP_id());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductsToCart(model);
            }
        });


    }

    private void addProductsToCart(modelClothing model) {
        String productId = model.p_id;
        String productName = model.p_name;
        String productAvailable = model.p_qty;
        String productPrice = model.p_price;

        Log.d("id","product id: "+productId+" item clicked is : "+productName+" and the price : "+productPrice+" and available stock : "+productAvailable);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FieldValue fr = FieldValue.increment(1);
        userID = fAuth.getCurrentUser().getUid();
        fDatabase.getReference().child("user_orders").child(userID).child(productId).child("p_id").setValue(productId);
        fDatabase.getReference().child("user_orders").child(userID).child(productId).child("p_name").setValue(productName);
        fDatabase.getReference().child("user_orders").child(userID).child(productId).child("p_price").setValue(productPrice);
    }



    @Override
    public int getItemCount() {
        return cList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clothing_Name, clothing_Available_Stock , clothing_Price,clothing_id;
        Button addToCartBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clothing_Name = itemView.findViewById(R.id.txtItemName);
            clothing_id = itemView.findViewById(R.id.txtProductId);
            clothing_Available_Stock= itemView.findViewById(R.id.txtNumberOfItemsAvailable);
            clothing_Price = itemView.findViewById(R.id.txtProductPrice);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);

        }
    }
}
