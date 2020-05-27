package calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyCalculator {

    private List<CurrencyRate> currencyRates;

    public BigDecimal calculateRate(BigDecimal amountInEuro, String currencyName) {
        initialize();

        List<CurrencyRate> desiredCurrencyRate = currencyRates.stream()
                .filter(rate -> rate.getName().equals(currencyName))
                .collect(Collectors.toList());

        if (desiredCurrencyRate.size() == 0) {
            throw new IllegalArgumentException();
        }

        if (desiredCurrencyRate.size() > 1) {
            throw new IllegalArgumentException();
        }

        CurrencyRate destinationCurrencyRate = desiredCurrencyRate.get(0);

        return amountInEuro.multiply(destinationCurrencyRate.getRate()).stripTrailingZeros();
    }

    private void initialize() {
        CurrenciesXmlParser xmlParser = new CurrenciesXmlParser();
        currencyRates = xmlParser.parseCurrencyRates();
    }
}
