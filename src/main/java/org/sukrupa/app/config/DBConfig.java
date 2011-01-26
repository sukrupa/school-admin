package org.sukrupa.app.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@ImportResource("classpath:transaction-config.xml")
public class DBConfig {

    private static final String BASE_PACKAGE = "org.sukrupa";
    
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.user}")
    private String jdbcUser;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        return sessionFactoryFrom(dataSource, hibernateProperties());
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(true);
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPassword);
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
            sessionFactory.setPackagesToScan(new String[]{BASE_PACKAGE,});
            sessionFactory.setHibernateProperties(hibernateProperties);
            sessionFactory.afterPropertiesSet();
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
