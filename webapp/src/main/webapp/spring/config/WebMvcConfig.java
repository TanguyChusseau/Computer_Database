package main.webapp.spring.config;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {WEBAPP_PATH})
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(JS_FILES).addResourceLocations(JS_PATH);
		registry.addResourceHandler(CSS_FILES).addResourceLocations(CSS_PATH);
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName(COOKIE);
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(LANG);
        return interceptor;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(I18N_MESSAGES);
        messageSource.setDefaultEncoding(ENCODING);
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(LOCALE);
        registry.addInterceptor(interceptor);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(VIEW_PREFIX);
        viewResolver.setSuffix(VIEW_SUFFIX);
        return viewResolver;
    }
    
}
