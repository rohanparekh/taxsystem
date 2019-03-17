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
        assertEquals(new BigDecimal(29.83).setScale(2, BigDecimal.ROUND_CEILING), calculationService.calculateBill(taxService.getOrder()));
    }

    @Test
    public void calculateBillInput2() throws IOException {
        taxService.createOrder(TaxServiceContants.inputFile02);
        assertEquals(new BigDecimal(65.15).setScale(2, BigDecimal.ROUND_CEILING), calculationService.calculateBill(taxService.getOrder()));
    }
}