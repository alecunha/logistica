package com.walmart.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.walmart.service" }, excludeFilters = { @Filter(Configuration.class) })
public class ServiceConfig extends JndiTemplate {

	@Autowired
	private Environment env;
}
