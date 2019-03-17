package org.myworks.service;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Category;
import org.myworks.model.Order;
import org.myworks.model.Product;
import org.myworks.service.CalculationService;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt.
 * Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
 *
 * When I purchase items I receive a receipt which lists the name of all the items and their price (including tax),
 * finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules
 * for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the
 * nearest 0.05) amount of sales tax.
 */
public class TaxService {

    private List<Product> productListList = new ArrayList<>();
    private Order order = new Order();
    private CalculationService calculationService = new CalculationService();
    private BigDecimal totalBill = BigDecimal.valueOf(0.0);
    /**
     * 1 book at 12.49
     * 1 music CD at 14.99
     * 1 chocolate bar at 0.85
     *
     * @param file
     * @throws IOException
     */
    public void createOrder(String file) throws IOException {
        Product product;
        File inputFile = readFile(file);
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = "";
        int lines = 1;

        while ((line = reader.readLine()) != null) {
            String[] splitOrderDetails = line.split(" at ");
            String productQuantity = splitOrderDetails[0].substring(0, splitOrderDetails[0].indexOf(" "));
            String productName = splitOrderDetails[0].substring(splitOrderDetails[0].indexOf(" "), splitOrderDetails[0].length());
            product = new Product(productName.trim());
            product.setProductId(lines);
            product.setQuantity(Integer.parseInt(productQuantity.trim()));
            product.setProductPrice(new BigDecimal(splitOrderDetails[1].trim()));
            productListList.add(product);
            lines++;
        }
        order.setOrderId(1);
        order.setProducts(productListList);
    }

    public void calculateBill() {
       totalBill = calculationService.calculateBill(order);
       System.out.println("Total Bill" + totalBill);
    }

    public File readFile(String filePath) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        return new File(classLoader.getResource(filePath).getPath());
    }

    public List<Product> getProductListList() {
        return productListList;
    }

    public Order getOrder() {
        return order;
    }
}
