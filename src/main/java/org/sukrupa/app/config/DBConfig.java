package org.sukrupa.app.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.sukrupa.app.DbServer;

import javax.sql.DataSource;
import java.util.Properties;

@ImportResource("classpath:transaction-config.xml")
public class DBConfig {

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        return sessionFactoryFrom(dataSource, hibernateProperties());
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource(DbServer dbServer) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(true);
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost/sukrupa");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean(initMethod = "start", destroyMethod = "shutDown")
    public DbServer dbServer() {
        return new DbServer();
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

}
