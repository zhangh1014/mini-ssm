package app.conf;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ImportResource("classpath:/spring/spring-mvc.xml")
@EnableWebMvc // 启用注解驱动的SpringMVC
@EnableAspectJAutoProxy // 启用切面的自动代理
@ComponentScan(basePackageClasses = { app.conf.springMvc._ComponentScan.class, app.controller._ComponentScan.class })
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 等同于<mvc:default-servlet-handler/>
		// 启动默认的Servlet，在web.xml中配置，将静态资源交给默认Servlet处理
		configurer.enable();
	}

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {

		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		// 异步请求时，支持Ajax传参，批量传参
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		mediaTypes.add(MediaType.TEXT_HTML);

		MappingJackson2HttpMessageConverter mjhmc = new MappingJackson2HttpMessageConverter();
		mjhmc.setSupportedMediaTypes(mediaTypes);

		List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
		list.add(mjhmc);

		RequestMappingHandlerAdapter rmha = new RequestMappingHandlerAdapter();
		rmha.setMessageConverters(list);

		return rmha;
	}

	@Bean
	@DependsOn(value = "lifecycleBeanPostProcessor")
	// 开启Shiro注解
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	@Bean
	// 开启Shiro注解
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

}
