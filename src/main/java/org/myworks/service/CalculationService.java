package org.myworks.service;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Order;
import org.myworks.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculationService {

    private BigDecimal totalSalesTax = BigDecimal.ZERO;

    public BigDecimal calculateBill(Order order) {
        return calculateTax(order).setScale(2, BigDecimal.ROUND_CEILING);
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
        return totalPrice.setScale(2, BigDecimal.ROUND_UP);
    }
}