package com.example.supermarket;

import android.widget.Button;
import android.widget.ImageButton;

public class modelCartFunction {

    String p_name, p_price, p_id;
    ImageButton removeProductsCartFunction;

    public modelCartFunction() {

    }

    public modelCartFunction(String p_name, String p_price, String p_id, ImageButton removeProductsCartFunction) {
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_id = p_id;
        this.removeProductsCartFunction = removeProductsCartFunction;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public ImageButton getRemoveProductsCartFunction() {
        return removeProductsCartFunction;
    }

    public void setRemoveProductsCartFunction(ImageButton removeProductsCartFunction) {
        this.removeProductsCartFunction = removeProductsCartFunction;
    }
}
