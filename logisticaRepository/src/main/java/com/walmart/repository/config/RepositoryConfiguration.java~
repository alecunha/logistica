package com.walmart.repository.config;

import java.util.Properties;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Repository module configuration. It basically enables transaction management
 * on a standard JPA EntityManager session.
 * <p>
 * This configuration uses a properties file that must be place in the root
 * classpath.
 * 
 * @see Configuration
 * @see EnableTransactionManagement
 * @see PropertySource
 */
@Configuration
@EnableJpaRepositories("com.walmart.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.walmart.repository" }, excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:jdbc.properties")
public class RepositoryConfiguration extends JndiTemplate {

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "")
	public DataSource dataSource() {

		final DriverManagerDataSource bds = new DriverManagerDataSource();
		bds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		bds.setUrl(env.getProperty("jdbc.url"));
		bds.setUsername(env.getProperty("jdbc.username"));
		bds.setPassword(env.getProperty("jdbc.password"));
		
		return bds;
	}
	
	@Bean
	@DependsOn("entityManagerFactory")
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
	    return new JpaTransactionManager(entityManagerFactory.getNativeEntityManagerFactory());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.walmart.repository" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	private Properties additionalProperties() {

		final Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		hibernateProperties.put("hibernate.cache.use_query_cache", "true");
		hibernateProperties.put("hibernate.cache.use_second_level_cache", "true");

		hibernateProperties.put("hibernate.c3p0.min_size", "5");
		hibernateProperties.put("hibernate.c3p0.max_size", "20");
		hibernateProperties.put("hibernate.c3p0.timeout", "36000");
		hibernateProperties.put("hibernate.c3p0.max_statements", "50");
		hibernateProperties.put("hibernate.c3p0.idle_test_period", "7200");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.format_sql", "true");
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		//hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");

		return hibernateProperties;
	}
}
