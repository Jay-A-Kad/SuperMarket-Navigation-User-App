package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class myAdapterCartFunction extends RecyclerView.Adapter<myAdapterCartFunction.MyViewHolder> {

    public ArrayList<modelCartFunction> funList;
    Context context;
    final FirebaseDatabase fDatabase = FirebaseDatabase.getInstance();
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    FirebaseUser user;
    String uId;

    public myAdapterCartFunction( Context context ,ArrayList<modelCartFunction> funList) {
        this.funList = funList;
        this.context = context;
    }

    @NonNull
    @Override
    public myAdapterCartFunction.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartV  = LayoutInflater.from(context).inflate(R.layout.cart_single_row , parent , false);
        return new myAdapterCartFunction.MyViewHolder(cartV);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapterCartFunction.MyViewHolder holder, int position) {
        modelCartFunction cartModel = funList.get(position);
        holder.cart_Name.setText( cartModel.getP_name());
        holder.cart_Price.setText( cartModel.getP_price());
        holder.cart_id.setText( cartModel.getP_id());

        holder.removeCartItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cartId = cartModel.p_id;
                String cartName = cartModel.p_name;
                String cartPrice = cartModel.p_price;

                Log.d("item removed is","product id: "+cartId+" item clicked is : "+cartName+" and the price : "+cartPrice);

                fAuth = FirebaseAuth.getInstance();
                fStore = FirebaseFirestore.getInstance();
                user = fAuth.getInstance().getCurrentUser();
                uId = user.getUid();
                DatabaseReference dRef = FirebaseDatabase.getInstance().getReference().child("user_orders").child(uId).child(cartId);
                dRef.getRef().removeValue();

                funList.clear();











            }
        });
    }


    private void removeProductsCartFunction(modelCartFunction cartModel) {



    }





    @Override
    public int getItemCount() {
        return funList.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cart_Name,cart_Price,cart_id;
        ImageButton removeCartItems;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_Name = itemView.findViewById(R.id.txtNameOfCartPdt);
            cart_id = itemView.findViewById(R.id.txtIdOfTheCartPdt);
            cart_Price = itemView.findViewById(R.id.txtPriceOfCartPdt);
            removeCartItems = itemView.findViewById(R.id.btnRemoveTheItemCart);

        }
    }
}
