package com.example.prototype_1;

public class ProductHolder {

    private Product product;
    private static final ProductHolder INSTANCE = new ProductHolder();

    public ProductHolder() {}

    public static ProductHolder getInstance() {
        return INSTANCE;
    }

    public void setProduct(Product p) {
        this.product = p;
    }

    public Product getProduct() {
        return this.product;
    }
}

//this should allow Controller and SearchApplication to share the Product, but not set up correctly