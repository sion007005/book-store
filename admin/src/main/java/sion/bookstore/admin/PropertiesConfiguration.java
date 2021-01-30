package sion.bookstore.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true,
	value = {
		"classpath:application-domain.properties",
		"classpath:application-domain-${spring.profiles.active}.properties"
	})
public class PropertiesConfiguration {
}
