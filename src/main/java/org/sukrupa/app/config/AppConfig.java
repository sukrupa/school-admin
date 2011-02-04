package org.sukrupa.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.sukrupa.platform.web.StringTemplateView;
import org.sukrupa.platform.web.TransactionHandlerInterceptor;

import java.io.IOException;
import java.util.Properties;

@Configuration
@Import({DBConfig.class})
public class AppConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setViewClass(StringTemplateView.class);
        viewResolver.setSuffix(".st");
        return viewResolver;
    }

    @Bean
    public HandlerMapping handlerMapping(PlatformTransactionManager platformTransactionManager) {
        DefaultAnnotationHandlerMapping handlerMapping = new DefaultAnnotationHandlerMapping();
        handlerMapping.setInterceptors(handlerInterceptors(platformTransactionManager));
        return handlerMapping;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setProperties(properties());
        return configurer;
    }

    @Bean
    public Properties properties() {
        try {
            Properties properties = new Properties();
            properties.load(new ClassPathResource("database.properties").getInputStream());
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] handlerInterceptors(PlatformTransactionManager platformTransactionManager) {
        return new Object[]{transactionHandlerInterceptor(platformTransactionManager)};
    }

    private TransactionHandlerInterceptor transactionHandlerInterceptor(PlatformTransactionManager platformTransactionManager) {
        return new TransactionHandlerInterceptor(platformTransactionManager);
    }
}
