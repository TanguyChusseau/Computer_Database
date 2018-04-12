package main.webapp.spring.config;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(BASE_PATH)
@PropertySource(value = {HIBERNATE_PROPERTIES})
public class WebDataSourceConfiguration {
	
	// Private fields
	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@Bean
	  public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty(DATABASE_DRIVER));
	    dataSource.setUrl(env.getProperty(DATABASE_URL));
	    dataSource.setUsername(env.getProperty(DATABASE_LOGIN));
	    dataSource.setPassword(env.getProperty(DATABASE_PASSWORD));
	    return dataSource;
	  }

	  /**
	   * Declare the JPA entity manager factory.
	   */
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    LocalContainerEntityManagerFactoryBean entityManagerFactory =
	        new LocalContainerEntityManagerFactoryBean();
	    
	    entityManagerFactory.setDataSource(dataSource);
	    entityManagerFactory.setPackagesToScan(env.getProperty(HIBERNATE_PACKAGES_TO_SCAN));
	    
	    // Vendor adapter
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
	    
	    // Hibernate properties
	    Properties additionalProperties = new Properties();
	    additionalProperties.put(HIBERNATE_DIALECT, 
	        env.getProperty(HIBERNATE_DIALECT));
	    additionalProperties.put(HIBERNATE_SHOW_SQL, 
	        env.getProperty(HIBERNATE_SHOW_SQL));
	    additionalProperties.put(HIBERNATE_HBM2DDL_AUTO, 
	        env.getProperty(HIBERNATE_HBM2DDL_AUTO));
	    entityManagerFactory.setJpaProperties(additionalProperties);
	    
	    return entityManagerFactory;
	  }

	  /**
	   * Declare the transaction manager.
	   */
	  @Bean
	  public JpaTransactionManager transactionManager() {
	    JpaTransactionManager transactionManager = 
	        new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(
	        entityManagerFactory.getObject());
	    return transactionManager;
	  }
	  
	  @Bean
	  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	    return new PersistenceExceptionTranslationPostProcessor();
	  }   

}
