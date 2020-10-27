package com.kevamdg.sr.config;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class PrimaryDBConfig {

	/*@Bean(name = "kioskfirstdataSource")
	@Primary
	@ConfigurationProperties(prefix = "kiosk.datasource")
	public DataSource primaryDataSource() throws IOException {

		return DataSourceBuilder.create().build();
	}
	*/
	@Bean(name = "kioskfirstdataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public org.apache.tomcat.jdbc.pool.DataSource primaryDataSource() {
		return (org.apache.tomcat.jdbc.pool.DataSource) DataSourceBuilder.create()
				.type(org.apache.tomcat.jdbc.pool.DataSource.class)
				.url("jdbc:mysql://172.16.9.105:3306/common_mdg").build();
	}

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean ds1EntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("kioskfirstdataSource") org.apache.tomcat.jdbc.pool.DataSource dataSource) throws IOException {

		return builder.dataSource(dataSource).packages("com.kevamdg.sr.entity").persistenceUnit("common_mdg").build();
	}

	@Bean(name="transactionManager")
	@Primary
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) throws IOException {

		return new JpaTransactionManager(entityManagerFactory);
	}
}
