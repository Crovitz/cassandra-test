package com.example.cass.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.data.cassandra")
public class CassandraProperties {
    private String contactPoints;
    private int port;
    private String keyspaceName;
    private String username;
    private String password;

    public CassandraProperties(String contactPoints, int port, String keyspaceName, String username, String password) {
        this.contactPoints = contactPoints;
        this.port = port;
        this.keyspaceName = keyspaceName;
        this.username = username;
        this.password = password;
    }

    public String getContactPoints() {
        return contactPoints;
    }

    public int getPort() {
        return port;
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
