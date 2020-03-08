package com.example.cass.configuration;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories
@EnableConfigurationProperties(value = CassandraProperties.class)
public class CassandraConfiguration extends AbstractCassandraConfiguration {
    private final CassandraProperties properties;

    public CassandraConfiguration(CassandraProperties properties) {
        this.properties = properties;
    }

    @Override
    protected AuthProvider getAuthProvider() {
        return new PlainTextAuthProvider(properties.getUsername(), properties.getPassword());
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(properties.getKeyspaceName()).ifNotExists());
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return properties.getKeyspaceName();
    }

    @Override
    public String getContactPoints() {
        return properties.getContactPoints();
    }

    @Override
    public int getPort() {
        return properties.getPort();
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {
                "com.example.cass.domain",
                "com.example.cass.infrastructure.repository"
        };
    }
}
