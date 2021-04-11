package com.example.supermarket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class myAdapterElectronic extends RecyclerView.Adapter<myAdapterElectronic.MyViewHolder> {


    public ArrayList<modelElectronic> eList;
    Context context;
    final FirebaseDatabase fDatabase = FirebaseDatabase.getInstance();
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;


    public myAdapterElectronic(Context context , ArrayList<modelElectronic> eList) {
        this.eList = eList;
        this.context = context;
    }

    @NonNull
    @Override
    public myAdapterElectronic.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.electronic_item , parent , false);
        return new myAdapterElectronic.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull myAdapterElectronic.MyViewHolder holder, int position) {
        modelElectronic model = eList.get(position);
        holder.electronic_Name.setText(model.getP_name());
        holder.electronic_Available_Stock.setText(model.getP_qty());
        holder.electronic_Price.setText(model.getP_price());
        holder.electronic_id.setText(model.getP_id());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductsToCart(model);
            }
        });

    }
    private void addProductsToCart(modelElectronic model) {
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
        {
            return eList.size();
    }

}

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView electronic_Name, electronic_Available_Stock, electronic_Price, electronic_id;
        Button addToCartBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            electronic_Name = itemView.findViewById(R.id.txtItemName);
            electronic_id = itemView.findViewById(R.id.txtProductId);
            electronic_Available_Stock = itemView.findViewById(R.id.txtNumberOfItemsAvailable);
            electronic_Price = itemView.findViewById(R.id.txtProductPrice);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);

        }
    }
    }
