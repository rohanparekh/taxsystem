package org.myworks.model;

public class Product {
    private int productId;
    private String productName;
    private Double productPrice;
    private boolean isImported;
    private boolean isSalesTaxApplicable;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public boolean isSalesTaxApplicable() {
        return isSalesTaxApplicable;
    }

    public void setSalesTaxApplicable(boolean salesTaxApplicable) {
        isSalesTaxApplicable = salesTaxApplicable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
