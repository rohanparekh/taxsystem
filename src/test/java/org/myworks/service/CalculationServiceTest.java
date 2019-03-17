package org.myworks.service;

import org.junit.Test;
import org.myworks.constants.TaxServiceContants;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CalculationServiceTest {

    CalculationService calculationService = new CalculationService();
    TaxService taxService = new TaxService();

    @Test
    public void calculateBillInput1() throws IOException {
        taxService.createOrder(TaxServiceContants.inputFile01);
        assertEquals(new Double(29.83), new Double(calculationService.calculateBill(taxService.getOrder())));
    }

    @Test
    public void calculateBillInput2() throws IOException {
        taxService.createOrder(TaxServiceContants.inputFile02);
        assertEquals(new Double(65.15), new Double(calculationService.calculateBill(taxService.getOrder())));
    }
}