package de.mlrdev.liquibasedemo.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Autowired
    private DataSource datasource;  // don't bother the error, it's defined in YAML-file

    @Bean(name = "springLiquibase")
    SpringLiquibase springLiquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(datasource);
        liquibase.setChangeLog("classpath:dbchangelogs/changelog-master.xml");
        return liquibase;
    }
}
