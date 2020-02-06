package org.openup.config;





import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Configuration
public class Internationalization extends WebMvcConfigurerAdapter{
	
	@Bean
	public LocaleResolver localeResolver() {  // here We need to determine default Locale of your application.
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() { //LocaleChangeInterceptor is a used to change the new Locale based on the value of the language parameter added to a request.
		
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
		}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {//To take this effect, we need to add the LocaleChangeInterceptor into the application’s registry interceptor.
		
		registry.addInterceptor(localeChangeInterceptor());
	}

}
