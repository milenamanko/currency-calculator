package calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyCalculatorTest {

    private final CurrencyCalculator currencyCalculator = new CurrencyCalculator();

    @Test
    void test() {
        //WHEN
        BigDecimal result = currencyCalculator.calculateRate(new BigDecimal("10"), "PLN");

        //THEN
        assertEquals(new BigDecimal("44.372"), result);
    }


}