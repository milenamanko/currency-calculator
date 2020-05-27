package calculator;

import org.jdom2.Attribute;

import java.math.BigDecimal;

public class CurrencyRate {

    private final String name;

    private final BigDecimal rate;

    private CurrencyRate(String name, BigDecimal rate) {
        this.name = name;
        this.rate = rate;
    }

    public static CurrencyRate buildCurrencyRate(Attribute currencyAttribute, Attribute valueAttribute) {
        return new CurrencyRate(currencyAttribute.getValue(), new BigDecimal(valueAttribute.getValue()));
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }

}
