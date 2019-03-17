package org.myworks.service;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Order;
import org.myworks.model.Product;

import java.util.List;

public class CalculationService {

    private double totalBill = 0.00;

    private double roundedDouble = 0.0;

    private double totalSalesTax = 0.00;

    public double calculateBill(Order order) {
        return calculateTax(order);
    }

    private double calculateTax(Order order) {
        double itemPrice;
        double importedTax = 0.00;
        double salesTaxPerItem = 0.00;

        List<Product> products = order.getProducts();

        for (Product product : products) {
            if (product.isTaxable()) {
                salesTaxPerItem = product.getProductPrice() * TaxServiceContants.SALES_TAX_AMOUNT;
                itemPrice = product.getProductPrice() + salesTaxPerItem;
            } else {
                itemPrice = product.getProductPrice();
            }

            itemPrice = calculateRoundedTax(itemPrice);

            if (product.isImported()) {
                importedTax = calculateRoundedTax(product.getProductPrice() * TaxServiceContants.IMPORTED_TAX_AMOUNT);
                itemPrice = itemPrice + importedTax;
            }

            totalSalesTax = totalSalesTax + salesTaxPerItem + importedTax;
            totalBill = totalBill + itemPrice;
        }
        System.out.println("Total sales tax: " + totalSalesTax);
        return totalBill;
    }

    private double calculateRoundedTax(double salesTax) {
        double roundToTwoDedimal = Math.round(salesTax * 100) / 100.0; // round to two decimal
        return Math.round(roundToTwoDedimal * 20) / 20.0; // round up to multiple of 0.05
    }

    public double getTotalBill() {
        return totalBill;
    }

    public double getRoundedDouble() {
        return roundedDouble;
    }

    public double getTotalSalesTax() {
        return totalSalesTax;
    }
}