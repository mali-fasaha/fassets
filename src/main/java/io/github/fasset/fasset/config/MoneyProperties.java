package io.github.fasset.fasset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties("money")
public class MoneyProperties {

    private String defaultCurrency="KES";

    public MoneyProperties() {
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public MoneyProperties setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyProperties that = (MoneyProperties) o;
        return Objects.equals(defaultCurrency, that.defaultCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultCurrency);
    }

    @Override
    public String toString() {
        return "MoneyProperties{" +
                "defaultCurrency='" + defaultCurrency + '\'' +
                '}';
    }
}
