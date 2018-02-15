package app.conf;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.lechisoft.minifw.jdbc.datasource.DataSourceProvider;
import org.lechisoft.minifw.jdbc.datasource.DataSourceProvider.DataSourceType;
import org.lechisoft.minifw.mybaits.SqlInterceptor;
import org.lechisoft.minifw.mybaits.SqlInterceptor.DataBaseType;
import org.lechisoft.minifw.security.MiniRealm;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import app.shiro.DbRealmData;

@Configuration
@ComponentScan(basePackageClasses = { app.conf.spring._ComponentScan.class, app.service._ComponentScan.class,
		app.mapper._ComponentScan.class })
@EnableAspectJAutoProxy
@EnableTransactionManagement
@MapperScan(basePackageClasses = { app.mapper._ComponentScan.class })
public class SpringConfig {
	@Bean
	public DataSource dataSource() {
		DataSource ds = DataSourceProvider.getDataSource(DataSourceType.c3p0);
		return ds;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource);
		return dstm;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource);

		// 指定别名包
		ssf.setTypeAliasesPackage("app.model");

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 指定mybatis配置文件
		// ssf.setConfigLocation(resolver.getResource("./mybatis/mybatis-config.xml"));
		// 指定mapper配置文件
		ssf.setMapperLocations(resolver.getResources("classpath:/mybatis/*Mapper.xml"));

		// SQL拦截器
		SqlInterceptor sqlInterceptor = new SqlInterceptor(DataBaseType.MYSQL);
		ssf.setPlugins(new Interceptor[] { sqlInterceptor });

		return ssf.getObject();
	}
	
	

	@Bean
	public DbRealmData dbRealmData() {
		return new DbRealmData();
	}

	@Bean
	public MiniRealm realm(DbRealmData dbRealmData) {
		MiniRealm realm = new MiniRealm(dbRealmData);
		return realm;
	}

	@Bean
	// Shiro 安全管理器
	public DefaultWebSecurityManager securityManager(MiniRealm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		return securityManager;
	}

	@Bean
	// Shiro 过滤器
	// Bean的名字必须与Web.xml中Filter代理的名字相同
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager);

		// 指定过滤链
		// 拦截器的优先级：从上到下，从左到右，如果有匹配的拦截器就会阻断并返回
		// anno:任何人都可以访问
		// authc:登录后才能访问，不包括Remember Me用户
		// user:登录后才能访问，包含Remember Me用户
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/master/**", "anon");
		filterChainDefinitionMap.put("/login/**", "anon");
		filterChainDefinitionMap.put("/**", "user");
		filter.setFilterChainDefinitionMap(filterChainDefinitionMap);

		// 指过滤链在身份认证失败时跳转的页面
		filter.setLoginUrl("/login");
		// 指过滤链在授权失败时跳转的页面
		filter.setUnauthorizedUrl("/unauthor");

		return filter;
	}

	@Bean
	// 负责同一管理Shiro Bean的初始化和销毁
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}
