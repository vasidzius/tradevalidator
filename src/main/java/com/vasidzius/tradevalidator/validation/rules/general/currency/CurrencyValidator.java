package com.vasidzius.tradevalidator.validation.rules.general.currency;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/**
 * The type Currency validator checks either currency match to ISO4217 ot not
 */
@Component
public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private static final Logger LOGGER = Logger.getLogger(CurrencyValidator.class.getSimpleName());

    private final List<String> validCurrencies = parseISO4217();

    @Override
    public boolean isValid(String pair, ConstraintValidatorContext context) {
        String first = getFirstCurrency(pair);
        String second = getSecondCurrency(pair);
        return isValidCurrency(first, context) && isValidCurrency(second, context);
    }

    /**
     * Validate given currency
     *
     * @param currency the currency
     * @param context  the context with return message
     * @return the boolean
     */
    public boolean isValidCurrency(String currency, ConstraintValidatorContext context) {
        if (!validCurrencies.contains(currency)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("%s currency doesn't match ISO 4217", currency))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private List<String> parseISO4217() {
        List<String> validCurrenciesResult = new ArrayList<>();
        File validCurrenciesFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ISO4217.xml")).getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(validCurrenciesFile);
            Element element = doc.getDocumentElement();
            NodeList list = element.getElementsByTagName("Ccy");
            if (list != null && list.getLength() > 0) {
                int length = list.getLength();
                for (int i = 0; i < length; i++) {
                    Node firstChild = list.item(i).getFirstChild();
                    if (firstChild != null) {
                        validCurrenciesResult.add(firstChild.getNodeValue());
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(SEVERE, e.getMessage());
        }
        return Collections.unmodifiableList(validCurrenciesResult);
    }

    /**
     * Gets second currency from CurrencyPair
     *
     * @param pair the pair
     * @return the second currency
     */
    public String getSecondCurrency(String pair) {
        return pair.substring(3);
    }

    /**
     * Gets first currency from CurrencyPair
     *
     * @param pair the pair
     * @return the first currency
     */
    public String getFirstCurrency(String pair) {
        return pair.substring(0, 3);
    }

}
