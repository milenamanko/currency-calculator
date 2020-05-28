package calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyCalculator {

    private List<CurrencyRate> currencyRates;

    public BigDecimal calculateRate(BigDecimal amountInEuro, String currencyName) {
        initialize();

        if (amountInEuro == null) {
            throw new IllegalArgumentException("Please provide valid amount in euro");
        }

        List<CurrencyRate> desiredCurrencyRateList = currencyRates.stream()
                .filter(rate -> rate.getName().equals(currencyName))
                .collect(Collectors.toList());

        if (desiredCurrencyRateList.size() == 0) {
            throw new IllegalArgumentException("Currency not available");
        }

        if (desiredCurrencyRateList.size() > 1) {
            throw new IllegalArgumentException("Currency rate is listed more than once");
        }

        CurrencyRate desiredCurrencyRate = desiredCurrencyRateList.get(0);

        return desiredCurrencyRate.getRate().multiply(amountInEuro).stripTrailingZeros();
    }

    private void initialize() {
        CurrenciesXmlParser xmlParser = new CurrenciesXmlParser();
        currencyRates = xmlParser.parseCurrencyRates();
    }
}
