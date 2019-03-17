package org.myworks.service;

import org.junit.Test;
import org.myworks.TaxService;
import org.myworks.constants.TaxServiceContants;

import java.io.IOException;

import static org.junit.Assert.*;

public class CalculationServiceTest {

    CalculationService calculationService = new CalculationService();
    TaxService taxService = new TaxService();

    @Test
    public void calculateBillInput1() throws IOException {
        taxService.createOrder(TaxServiceContants.inputFile01);
        assertEquals(29.829, calculationService.calculateBill(taxService.getOrder()));
    }

    @Test
    public void calculateBillInput2() throws IOException {
        taxService.createOrder(TaxServiceContants.inputFile02);
        assertEquals(65.15, calculationService.calculateBill(taxService.getOrder()));
    }
}