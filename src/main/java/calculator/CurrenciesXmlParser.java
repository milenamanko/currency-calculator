package calculator;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrenciesXmlParser {

    private static final Logger LOGGER = Logger.getLogger(CurrenciesXmlParser.class.getName());

    public static final int CURRENCY_NAME_INDEX = 0;
    public static final int CURRENCY_RATE_INDEX = 1;
    public static final String CURRENCIES_XML_PATH = "src/main/resources/currency-rates.xml";

    public List<CurrencyRate> parseCurrencyRates() {
        SAXBuilder builder = new SAXBuilder();
        File file = new File(CURRENCIES_XML_PATH);
        List<CurrencyRate> currencyRates = new ArrayList<>();

        try {
            Document document = builder.build(file);
            Element rootNode = document.getRootElement();
            Element cubeNode = getNode(rootNode);
            Element dateNode = getNode(cubeNode);

            for (Content content : dateNode.getContent()) {
                if (content instanceof Element) {
                    Attribute currencyAttribute = getAttribute((Element) content, CURRENCY_NAME_INDEX);
                    Attribute valueAttribute = getAttribute((Element) content, CURRENCY_RATE_INDEX);

                    currencyRates.add(CurrencyRate.buildCurrencyRate(currencyAttribute, valueAttribute));
                }
            }

        } catch (JDOMException | IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while parsing currency rates XML file", e);
            e.printStackTrace();
        }

        return currencyRates;
    }

    private Attribute getAttribute(Element content, int currencyNameIndex) {
        return content.getAttributes().get(currencyNameIndex);
    }

    private Element getNode(Element rootNode) {
        return rootNode.getChild("Cube", Namespace.getNamespace("http://www.ecb.int/vocabulary/2002-08-01/eurofxref"));
    }
}