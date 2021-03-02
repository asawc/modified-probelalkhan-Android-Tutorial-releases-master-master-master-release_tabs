package myapp.model;

import com.google.gson.annotations.SerializedName;

public class ProductProductsRelease {

    @SerializedName("id")
    private int productId;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("productname")
    private String productName;

    @SerializedName("productsymbol")
    private String productSymbol;

    public int getId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return productName;
    }

    public String getSymbol() {
        return productSymbol;
    }

}
