package myapp.model;

public class ProductRelease {

    //@SerializedName("id")
    private final Product product;
    private final ProductStatus status;
    private final int req_quantity;

    public ProductRelease(Product product, ProductStatus status, int req_quantity) {
        this.product = product;
        this.status = status;
        this.req_quantity = req_quantity;
    }

    public Product getProduct() {
        return product;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public int getReqQuantity() {
        return req_quantity;
    }
}
