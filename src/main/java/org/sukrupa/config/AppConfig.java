package org.sukrupa.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ImportResource("classpath:transaction-config.xml")
public class AppConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactoryFrom(dataSource(), hibernateProperties());
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(true);
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost/sukrupa");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
        return properties;
    }

    private SessionFactory sessionFactoryFrom(DataSource dataSource, Properties hibernateProperties) {
        try {
            AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setPackagesToScan(new String[]{"org.sukrupa",});
            sessionFactory.setHibernateProperties(hibernateProperties);
            sessionFactory.afterPropertiesSet();
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateSchema() {
//        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
//        config.addAnnotatedClass(Student.class);
//        Properties properties = hibernateProperties();
//        properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
//        properties.put("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/xdb");
//        properties.put("hibernate.connection.username", "sa");
//        properties.put("hibernate.connection.password", "");
//        SchemaExport schemaExport = new SchemaExport(config, properties);
//        schemaExport.setOutputFile("foo");
//        schemaExport.create(true, true);
    }
}
