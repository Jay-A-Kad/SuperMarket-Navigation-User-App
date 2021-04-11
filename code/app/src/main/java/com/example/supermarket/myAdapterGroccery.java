package com.example.supermarket;

import android.content.Context;
import android.graphics.ColorSpace;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myAdapterGroccery extends RecyclerView.Adapter<myAdapterGroccery.MyViewHolder> {


    public ArrayList<modelGroccery> gList;

    final FirebaseDatabase fDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference newDb = FirebaseDatabase.getInstance().getReference();
    Context context;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;



    public myAdapterGroccery(Context context , ArrayList<modelGroccery> gList) {
        this.gList = gList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.groccery_item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        modelGroccery model = gList.get(position);
        holder.grocerry_Name.setText(model.getP_name());
        holder.grocerry_Available_Stock.setText(model.getP_qty());
        holder.grocerry_Price.setText(model.getP_price());
        holder.groccery_id.setText(model.getP_id());


        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                add products to cart
                addProductsToCart(model);
            }
        });

    }

    private void addProductsToCart(modelGroccery model) {
//        gettimg data from model
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
        return gList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView grocerry_Name, grocerry_Available_Stock , grocerry_Price,groccery_id;

        Button addToCartBtn;
        ImageView item_is_checked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            groccery_id = itemView.findViewById(R.id.txtProductId);
            grocerry_Name = itemView.findViewById(R.id.txtItemName);
            grocerry_Available_Stock = itemView.findViewById(R.id.txtNumberOfItemsAvailable);
            grocerry_Price = itemView.findViewById(R.id.txtPriceOfItems);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            item_is_checked = itemView.findViewById(R.id.itemChecked);

        }



    }



}
