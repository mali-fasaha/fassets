package io.github.fasset.fasset.accounts.nomenclature.properties;

import io.github.fasset.fasset.kernel.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Implements common methods of the AccountIdService interface
 */
public abstract class AbstractAccountIdService implements AccountIdService {

    // Using external configuration
    protected Properties accountConfigProperties;

    protected AbstractAccountIdService(String propertiesFile) {

        String source = propertiesFile == null ? "account-id" : propertiesFile;

        accountConfigProperties = PropertiesUtils.fetchConfigProperties(source);
    }

    private static final Logger log = LoggerFactory.getLogger(AbstractAccountIdService.class);

    protected String formatKey(String propertyKey, String transaction, String element) {
        return String.format("%s.%s.%s", propertyKey.toLowerCase(), transaction, element).replace(" ", "-").replace("&", "and");
    }

    /**
     * Using the currency code used in the fixed assets value at cost, the currency's ISO 4217 code, this method generates
     * the unique code to be used in the account number sequence after the service outlet code
     *
     * @param currencyCode ISO 4217 currency code used to retrieve account number sequence code
     * @return Account number sequence code to follow the service outlet nomenclature
     */
    @Override
    public String getCurrencyCode(String currencyCode) {

        log.debug("Fetching currency code for Currency : {}", currencyCode);

        String code = accountConfigProperties.getProperty(currencyCode);

        log.debug("Currency code for ISO4217 currency code value {} resolved as {}", currencyCode, code);

        return code;
    }
}
