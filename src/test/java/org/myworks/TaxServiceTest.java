package org.myworks;

import org.junit.Before;
import org.junit.Test;
import org.myworks.constants.TaxServiceContants;
import org.myworks.service.TaxService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxServiceTest {

    TaxService service;

    @Before
    public void setUp() throws Exception {
        service = new TaxService();
    }

    @Test
    public void testReadFile() {

        File file = service.readFile(TaxServiceContants.inputFile01);
        assertEquals("input01.txt", file.getName());
    }

    @Test
    public void testCreateOrderSize() throws IOException {
        service.createOrder(TaxServiceContants.inputFile01);
        assertEquals(3, service.getProductListList().size());
    }

    @Test
    public void testProductList() throws IOException {
        service.createOrder(TaxServiceContants.inputFile01);
        assertEquals(1, service.getProductListList().get(0).getQuantity());
        assertEquals("book", service.getProductListList().get(0).getProductName());
        assertEquals(new Double(12.49), new Double(service.getProductListList().get(0).getProductPrice()));
        assertEquals(false, service.getProductListList().get(0).isTaxable());
        assertEquals(false, service.getProductListList().get(0).isImported());

        assertEquals(1, service.getProductListList().get(1).getQuantity());
        assertEquals("music CD", service.getProductListList().get(1).getProductName());
        assertEquals(new Double(14.99), new Double(service.getProductListList().get(1).getProductPrice()));
        assertEquals(true, service.getProductListList().get(1).isTaxable());
        assertEquals(false, service.getProductListList().get(1).isImported());


        assertEquals(20, service.getProductListList().get(2).getQuantity());
        assertEquals("chocolate bar", service.getProductListList().get(2).getProductName());
        assertEquals(new Double(0.85), new Double(service.getProductListList().get(2).getProductPrice()));
        assertEquals(false, service.getProductListList().get(2).isTaxable());
        assertEquals(false, service.getProductListList().get(2).isImported());
    }

    @Test
    public void testImportedInput() throws IOException {

        service.createOrder(TaxServiceContants.inputFile02);

        assertEquals(1, service.getProductListList().get(0).getQuantity());
        assertEquals("imported box of chocolates", service.getProductListList().get(0).getProductName());
        assertEquals(new Double(10.00), new Double(service.getProductListList().get(0).getProductPrice()));
        assertEquals(false, service.getProductListList().get(0).isTaxable());
        assertEquals(true, service.getProductListList().get(0).isImported());

        assertEquals(1, service.getProductListList().get(1).getQuantity());
        assertEquals("imported bottle of perfume", service.getProductListList().get(1).getProductName());
        assertEquals(new Double(47.50), new Double(service.getProductListList().get(1).getProductPrice()));
        assertEquals(true, service.getProductListList().get(1).isTaxable());
        assertEquals(true, service.getProductListList().get(1).isImported());
    }

    @Test
    public void testOrder() throws IOException {
        service.createOrder(TaxServiceContants.inputFile01);
        assertEquals(1, service.getOrder().getOrderId());
        assertEquals(3, service.getOrder().getProducts().size());
    }
}