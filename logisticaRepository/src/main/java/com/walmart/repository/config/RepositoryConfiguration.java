package com.walmart.repository.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
    @Autowired
	@DependsOn("dataSource")
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        vendorAdapter.setDatabase(Database.H2);
 
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.walmart.repository");
        factory.setDataSource(dataSource);
 
        factory.setJpaProperties(additionalProperties());
 
        factory.afterPropertiesSet();
 
        return factory.getObject();
    }
 
    @Bean
    @Autowired
    @DependsOn("entityManagerFactory")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        txManager.setEntityManagerFactory(entityManagerFactory);
        txManager.setJpaDialect(jpaDialect);
        return txManager;
    }
    
	private Properties additionalProperties() {

		final Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		hibernateProperties.put("hibernate.cache.use_query_cache", "true");
		hibernateProperties.put("hibernate.cache.use_second_level_cache", "true");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return hibernateProperties;
	}
}