package myapp.model;

import com.google.gson.annotations.SerializedName;

public class ProductsRelease {

    private ProductProductsRelease product;

    private int status;

    private int quantity;

//    private String city;

//    @SerializedName("zipcode")
//    private String zipCode;


    public ProductProductsRelease getProduct() {
        return product;
    }

    public int getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

}
