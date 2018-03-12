package io.github.fasset.fasset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "temp-uploads";

    public String getLocation() {
        return location;
    }

    public StorageProperties setLocation(String location) {
        this.location = location;
        return this;
    }
}
