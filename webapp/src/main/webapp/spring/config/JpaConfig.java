package main.webapp.spring.config;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {PERSISTENCE_PATH})
@PropertySource(HIBERNATE_PROPERTIES)
public class JpaConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(DATABASE_DRIVER));
		dataSource.setUrl(env.getProperty(DATABASE_URL));
		dataSource.setUsername(env.getProperty(DATABASE_LOGIN));
		dataSource.setPassword(env.getProperty(DATABASE_PASSWORD));
		return dataSource;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		// JPA Vendor
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		// Hibernate properties
		Properties additionalProperties = new Properties();
		additionalProperties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
		additionalProperties.put(HIBERNATE_SHOW_SQL,env.getProperty(HIBERNATE_SHOW_SQL));
		additionalProperties.put(HIBERNATE_HBM2DDL_AUTO, env.getProperty(HIBERNATE_HBM2DDL_AUTO));

		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(env.getProperty(PACKAGES_TO_SCAN));
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(additionalProperties);
		return entityManagerFactory;
	}

}
