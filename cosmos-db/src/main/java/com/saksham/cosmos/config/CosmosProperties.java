package com.saksham.cosmos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "spring.data.cosmos")
public class CosmosProperties {

    private String uri;

    private String key;

    private String secondaryKey;

    private boolean queryMetricsEnabled;

    @PostConstruct
    public void validate() {
        System.out.println("Cosmos URI: " + uri);
        System.out.println("Cosmos Key: " + key);
        if (uri == null || uri.trim().isEmpty()) {
            throw new IllegalArgumentException("Cosmos URI must be provided.");
        }
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Cosmos key must be provided.");
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecondaryKey() {
        return secondaryKey;
    }

    public void setSecondaryKey(String secondaryKey) {
        this.secondaryKey = secondaryKey;
    }

    public boolean isQueryMetricsEnabled() {
        return queryMetricsEnabled;
    }

    public void setQueryMetricsEnabled(boolean enableQueryMetrics) {
        this.queryMetricsEnabled = enableQueryMetrics;
    }
}