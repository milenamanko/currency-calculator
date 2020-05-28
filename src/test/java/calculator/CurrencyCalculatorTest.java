package calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyCalculatorTest {

    private final CurrencyCalculator currencyCalculator = new CurrencyCalculator();

    @Test
    void calculateRateGivenValidData() {
        //WHEN
        BigDecimal result = currencyCalculator.calculateRate(new BigDecimal("10"), "PLN");

        //THEN
        assertEquals(new BigDecimal("44.372"), result);
    }

    @Test
    void calculateRateGivenNonExistingCurrency() {
        assertThrows(IllegalArgumentException.class,
                () -> currencyCalculator.calculateRate(new BigDecimal("5"), "XCD"),
                "Currency not available");
    }

    @Test
    void calculateRateGivenNullCurrencyName() {
        assertThrows(IllegalArgumentException.class,
                () -> currencyCalculator.calculateRate(new BigDecimal("5"), null),
                "Currency not available");
    }

    @Test
    void calculateRateGivenNullCurrencyAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> currencyCalculator.calculateRate(null, "USD"),
                "Please provide valid amount in euro");
    }
}