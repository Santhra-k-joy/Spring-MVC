package com.epsilon.training.cfg;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.epsilon.training.entity.Product;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
@EnableWebMvc
@PropertySource("classpath:jdbc-info.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.epsilon.training.web","com.epsilon.training.dao","com.epsilon.training.aop"})
public class AppConfig implements WebApplicationInitializer{ //but we need to tell tomcat that this class is the config file

	public AppConfig()
	{
		log.debug("AppConfig instantiated");
	}

	public void onStartup(ServletContext servletContext) throws ServletException {

    log.debug("AppConfig.onStartup() called");
		//we are creating new spring container and register this apconfig with the container
	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(AppConfig.class);
	
	Dynamic ds = servletContext.addServlet("ds", new DispatcherServlet(ctx));
	ds.addMapping("/");
	ds.setLoadOnStartup(1);
	}

	@Value("${jdbc.connection.driverClassName}")
	private String driverClassName;	

	@Value("${jdbc.connection.url}")
	private String url;
	@Value("${jdbc.connection.username}")
	private String username;
	@Value("${jdbc.connection.password}") 
	private String password;

	
	@Bean
	public HibernateTransactionManager txMgr(SessionFactory sf)
	{
		return new HibernateTransactionManager(sf);
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource)  	
	{
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource);
		lsfb.setAnnotatedClasses(Product.class);
		return  lsfb;
	}
	
	
	
	@Bean
	public  HibernateTemplate template(SessionFactory sessionFactory)
	{
		return new HibernateTemplate(sessionFactory);
	}

	

	@Bean

	public DataSource dataSource() {

	BasicDataSource bds = new BasicDataSource();

	bds.setDriverClassName(driverClassName);

	bds.setUrl(url);

	bds.setUsername (username);
	bds.setPassword(password);

	bds.setMaxTotal(50);
	bds.setInitialSize(5);

	bds.setMaxWaitMillis (3000);
	bds.setMaxIdle(5);

	return bds;
	}
}
