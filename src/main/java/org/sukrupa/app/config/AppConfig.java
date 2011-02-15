package org.sukrupa.app.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.sukrupa.platform.web.StringTemplateView;

import java.io.IOException;
import java.util.Properties;

import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE;

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
    public HandlerMapping handlerMapping(OpenSessionInViewInterceptor interceptor) {
        DefaultAnnotationHandlerMapping handlerMapping = new DefaultAnnotationHandlerMapping();
        handlerMapping.setInterceptors(new Object[]{interceptor});
        return handlerMapping;
    }

    @Bean
    public OpenSessionInViewInterceptor openSessionInViewInterceptor(SessionFactory sessionFactory) {
        OpenSessionInViewInterceptor interceptor = new OpenSessionInViewInterceptor();
        interceptor.setSessionFactory(sessionFactory);
        return interceptor;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_OVERRIDE);
        configurer.setProperties(properties());
        return configurer;
    }

    @Bean
    public Properties properties() {
        try {
            Properties properties = new Properties();
            properties.load(new ClassPathResource("app.properties").getInputStream());
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setDefaultErrorView("error");
        return resolver;
    }

}
