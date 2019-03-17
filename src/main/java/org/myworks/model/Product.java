package org.myworks.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private boolean isImported;
    private boolean isTaxable;
    private int quantity;

    public Product(String productName) {
        this.productName = productName;
        this.setTaxable(setProductTaxableStatus());
        this.setImported(setProductImportedStatus());
    }

    private boolean setProductImportedStatus() {
        String nameUpperCase = this.productName.toUpperCase();
       return nameUpperCase.contains(Category.IMPORTED.name());
    }

    private boolean setProductTaxableStatus() {
        String nameUpperCase = this.productName.toUpperCase();
        if(nameUpperCase.contains(Category.MEDICINE.name())
        || nameUpperCase.contains(Category.PILLS.name())
        || nameUpperCase.contains(Category.FOOD.name())
        || nameUpperCase.contains(Category.BOOK.name())
        || nameUpperCase.contains(Category.CHOCOLATE.name())) {
            return false;
        }
        return true;
    }

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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isTaxable() {
        return isTaxable;
    }

    public void setTaxable(boolean taxable) {
        isTaxable = taxable;
    }
}
