package lab.dsvnkna.mobile_lab.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lab.dsvnkna.mobile_lab.entities.Product;

public class ProductList{
    @SerializedName("result")
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}