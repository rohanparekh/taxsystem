package org.myworks.service;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Order;
import org.myworks.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculationService {

    private double totalSalesTax;

    private double roundedDouble = 0.0;

    public double calculateBill(Order order) {
        return calculateTax(order);
    }

    private double calculateTax(Order order) {
        double totalPrice = 0.00;
        double taxPerItem;
        double importedTax;
        double salesTax=0.00;

        List<Product> products = order.getProducts();

        for (Product product : products) {
            if (product.isTaxable()) {
                taxPerItem = product.getProductPrice() + product.getProductPrice() * TaxServiceContants.SALES_TAX_AMOUNT;
            } else {
                taxPerItem = product.getProductPrice();
            }
            if (product.isImported()) {
                importedTax = product.getProductPrice() * TaxServiceContants.IMPORTED_TAX_AMOUNT;
                taxPerItem = taxPerItem + importedTax;
            }
            totalPrice = totalPrice + taxPerItem;
        }

        return calculateRoundedAmount(totalPrice);

    }

    public double calculateRoundedAmount(double totalPrice) {
        roundedDouble = Math.round(totalPrice * 100) / 100.0; // round up to multiple of 0.05
        return roundedDouble;
    }

//    public boolean isRoundingNeeded(BigDecimal totalPrice) {
//        totalPrice = totalPrice.setScale(2, RoundingMode.CEILING);
//        BigDecimal fractionalPart = totalPrice.remainder( BigDecimal.ONE ); // Result:  0.4523434
//        double checkDecimalFraction = fractionalPart.setScale(2, RoundingMode.CEILING).doubleValue() / 0.05;
//        if(checkDecimalFraction == 0.99 || checkDecimalFraction == 0.00) {
//            return false;
//        }
//        return true;
//    }

    public double getTotalSalesTax() {
        return totalSalesTax;
    }

    public double getRoundedDouble() {
        return roundedDouble;
    }
}