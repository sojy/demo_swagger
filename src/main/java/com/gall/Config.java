package com.gall;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class Config {

	@Autowired
	private Environment env;


	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		additionalProperties.put("hibernate.jdbc.lob.non_contextual_creation",env.getProperty("hibernate.jdbc.lob.non_contextual_creation"));
		entityManagerFactory.setJpaProperties(additionalProperties);
		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/*
	 * @Bean public LocalSessionFactoryBean sessionFactory() {
	 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource());
	 * sessionFactory.setPackagesToScan(env.getProperty(
	 * "entitymanager.packagesToScan")); Properties hibernateProperties = new
	 * Properties(); hibernateProperties.put("hibernate.dialect",
	 * env.getProperty("hibernate.dialect"));
	 * hibernateProperties.put("hibernate.show_sql",
	 * env.getProperty("hibernate.show_sql"));
	 * hibernateProperties.put("hibernate.jdbc.lob.non_contextual_creation",env.
	 * getProperty("hibernate.jdbc.lob.non_contextual_creation")); //
	 * hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
	 * sessionFactory.setHibernateProperties(hibernateProperties); return
	 * sessionFactory; }
	 * 
	 * 
	 * @Bean public HibernateTransactionManager transactionManager() {
	 * HibernateTransactionManager txManager = new HibernateTransactionManager();
	 * txManager.setSessionFactory(sessionFactory().getObject()); return txManager;
	 * }
	 */
	 

	/*
	 * @Bean public InternalResourceViewResolver jspViewResolver() {
	 * InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	 * resolver.setPrefix("/views/"); resolver.setSuffix(".jsp"); return resolver; }
	 */
	
	/*
	 * @Bean public JdbcTemplate jdbcTemplate(DataSource dataSource) { JdbcTemplate
	 * jdbcTemplate = new JdbcTemplate(dataSource);
	 * jdbcTemplate.setResultsMapCaseInsensitive(true); return jdbcTemplate; }
	 */
	
	
   
}
