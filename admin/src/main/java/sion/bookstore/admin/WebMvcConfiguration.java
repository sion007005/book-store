package sion.bookstore.admin;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import sion.bookstore.domain.dispatcher.LoginInterceptor;

import java.util.List;

@Configuration
@EnableWebMvc
@Order(0)
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;

	private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	public WebMvcConfiguration(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
		this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**", "/assets/**")
				.addResourceLocations("classpath:/statics/", "classpath:/assets/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
//		registry.addInterceptor(webRequestUserTrackingInterceptor).addPathPatterns("/**").excludePathPatterns("/");
//		registry.addInterceptor(serviceMaintenanceInterceptor).addPathPatterns("/**").excludePathPatterns("/service/**","/static/**");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public IDialect conditionalCommentDialect() {
		return new Java8TimeDialect();
	}

	@Bean
	public MappingJackson2JsonView jsonView(){
		return new MappingJackson2JsonView();
	}
}


