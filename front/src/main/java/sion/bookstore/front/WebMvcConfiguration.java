package sion.bookstore.front;

import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import sion.bookstore.front.interceptor.LoginInterceptor;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
@Order(0)
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	private final LoginInterceptor loginInterceptor;

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
//		registry.addInterceptor(webRequestAuthInterceptor).addPathPatterns("/**");
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


