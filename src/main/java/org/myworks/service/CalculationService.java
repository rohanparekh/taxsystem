package org.myworks.service;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Order;
import org.myworks.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculationService {

    private BigDecimal totalSalesTax = BigDecimal.ZERO;

    private double roundedDouble = 0.0;

    public BigDecimal calculateBill(Order order) {
        return calculateTax(order);
    }

    private BigDecimal calculateTax(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal taxPerItem;
        BigDecimal importedTax;

        List<Product> products = order.getProducts();

        for (Product product : products) {
            taxPerItem = new BigDecimal(0);
            importedTax = new BigDecimal(0);
            if (product.isTaxable()) {
                taxPerItem = product.getProductPrice().add(product.getProductPrice().multiply(TaxServiceContants.SALES_TAX_AMOUNT));
            } else {
                taxPerItem = product.getProductPrice();
            }

            if (product.isImported()) {
                importedTax = product.getProductPrice().multiply(TaxServiceContants.IMPORTED_TAX_AMOUNT);
            }
            totalPrice = totalPrice.add(taxPerItem);
            totalPrice = totalPrice.add(importedTax);
        }

        calculateRoundedAmount(totalPrice);
        isRoundingNeeded(totalPrice);
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_UP);
        return totalPrice;
    }

    public void calculateRoundedAmount(BigDecimal totalPrice) {
        double roundingAmount = totalPrice.doubleValue();
        int precision = 20;
        roundedDouble = Math.round(roundingAmount * 20) / 20.0; // round up to multiple of 0.05
    }

    public boolean isRoundingNeeded(BigDecimal totalPrice) {
        totalPrice = totalPrice.setScale(2, RoundingMode.CEILING);
        BigDecimal fractionalPart = totalPrice.remainder( BigDecimal.ONE ); // Result:  0.4523434
        double checkDecimalFraction = fractionalPart.setScale(2, RoundingMode.CEILING).doubleValue() / 0.05;
        if(checkDecimalFraction == 0.99 || checkDecimalFraction == 0.00) {
            return false;
        }
        return true;
    }

    public BigDecimal getTotalSalesTax() {
        return totalSalesTax;
    }

    public double getRoundedDouble() {
        return roundedDouble;
    }
}