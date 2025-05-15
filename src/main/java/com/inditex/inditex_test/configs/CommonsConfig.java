package com.inditex.inditex_test.configs;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.inditex.inditex_test.serialization.ObjectMapperFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class CommonsConfig {

  /**
   * Creates a mapper for object serialization/deserialization.
   *
   * @return The mapper to use.
   */
  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    return new ObjectMapperFactory().build();
  }

  /**
   * Populates the database with the schema and data only in environment local
   *
   * @param connectionFactory the connection factory
   * @return the connection factory initializer
   */
  @Bean
  ConnectionFactoryInitializer initializerSchemaH2(ConnectionFactory connectionFactory) {
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.addScript(new ClassPathResource("h2/schema.sql"));
    databasePopulator.addScript(new ClassPathResource("h2/data.sql"));
    initializer.setDatabasePopulator(databasePopulator);
    initializer.setEnabled(true);

    return initializer;
  }
}
