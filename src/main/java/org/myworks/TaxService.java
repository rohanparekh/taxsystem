package org.myworks;

import org.myworks.constants.TaxServiceContants;
import org.myworks.model.Order;
import org.myworks.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt.
 * Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
 */
public class TaxService {

    private List<Product> productListList = new ArrayList<>();
    private Order order = new Order();

    /**
     * 1 book at 12.49
     * 1 music CD at 14.99
     * 1 chocolate bar at 0.85
     *
     * @param inputFile01
     * @throws IOException
     */
    public void createOrder(String inputFile01) throws IOException {
        Product product;
        File inputFile = readFile(TaxServiceContants.inputFile01);
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = "";
        int lines = 1;

        while ((line = reader.readLine()) != null) {
            String[] splitOrderDetails = line.split(" at ");
            String productQuantity = splitOrderDetails[0].substring(0, splitOrderDetails[0].indexOf(" "));
            String productName = splitOrderDetails[0].substring(splitOrderDetails[0].indexOf(" "), splitOrderDetails[0].length());
            product = new Product();
            product.setProductId(lines);
            product.setQuantity(Integer.parseInt(productQuantity.trim()));
            product.setProductName(productName.trim());
            product.setProductPrice(Double.valueOf(splitOrderDetails[1].trim()));
            productListList.add(product);
            lines++;
        }
        order.setOrderId(1);
        order.setProducts(productListList);
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
