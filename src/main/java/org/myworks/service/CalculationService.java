package org.myworks.service;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Order;
import org.myworks.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class CalculationService {

    public BigDecimal calculateBill(Order order) {
        return calculateTax(order);
    }

    private BigDecimal calculateTax(Order order) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        List<Product> products = order.getProducts();
        for(Product product : products) {
            if(product.isTaxable()) {
                totalPrice.add(product.getProductPrice().multiply(TaxServiceContants.SALES_TAX_AMOUNT));
            } else {
                totalPrice.add(product.getProductPrice());
            }

            if(product.isImported()) {
                totalPrice.add(product.getProductPrice().multiply(TaxServiceContants.IMPORTED_TAX_AMOUNT));
            }
        }
        return totalPrice;
    }
}