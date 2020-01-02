/**
 * FileName: ShiroConfig
 * Author:   小江
 * Date:     2019/12/22 16:30
 * History:
 */
package com.zhbit.xiaojiang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhbit.xiaojiang.component.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{

	//这个bean是为了使用shiro权限标签
	@Bean(name = "shiroDialect")
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}

	//创建ShiroFilterFactoryBean
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//设置请求资源的权限访问控制，通过Shiro的内置过滤器实现
		Map<String,String> filterMap = new LinkedHashMap<String,String>();
		//放行请求路径
		filterMap.put("/toLogin","anon");
		filterMap.put("/login","anon");
		filterMap.put("/images/*","anon");
		filterMap.put("/js/*","anon");
		filterMap.put("/css/*","anon");
		filterMap.put("/assets/**/*","anon");
		filterMap.put("/bootstrap-3.3.7/**/*","anon");
		filterMap.put("/font-awesome-4.7.0/**/*","anon");
		//filterMap.put("/demand:edit","perms[]")
		//使用通配符的方式进行全部请求路径拦截的
		filterMap.put("/**","authc");

		//设置认证拦截的请求
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		//设置认证被拦截后跳转的页面请求
		shiroFilterFactoryBean.setLoginUrl("/toLogin");

		return shiroFilterFactoryBean;
	}

	//创建DefaultWebSecurityManager
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联一个Realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	//创建Realm
	@Bean(name = "userRealm")
	public UserRealm getRealm(){
		return new UserRealm();
	}

}