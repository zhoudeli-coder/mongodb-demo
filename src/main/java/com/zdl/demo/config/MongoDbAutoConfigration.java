package com.zdl.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoDbAutoConfigration  extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "test";
    }
    @Override
    public boolean autoIndexCreation() {
        return true;
    }
}
