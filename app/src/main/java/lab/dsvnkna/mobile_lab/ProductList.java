package lab.dsvnkna.mobile_lab;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductList {
    @SerializedName("result")
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}