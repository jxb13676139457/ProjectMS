/**
 * FileName: WebConfig
 * Author:   小江
 * Date:     2019/12/18 21:20
 * History:
 */
package com.zhbit.xiaojiang.config;

import com.zhbit.xiaojiang.component.MyLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/18 22:58
	*Description  注册自定义的区域信息解析bean到容器中
	*/
	@Bean
	public LocaleResolver localeResolver(){
		return new MyLocaleResolver();
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/22 22:20
	*Description  设置访问项目根目录的指定默认访问页
	*/
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	@Value("${uploadFile.resourceHandler}")
	private String resourceHandler;
	@Value("${uploadFile.filePath}")
	private String filePath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//就是说 url 中出现 resourceHandler 匹配时，则映射到 location 中去,location 相当于虚拟路径
		//映射本地文件时，开头必须是 file:/// 开头，表示协议
		registry.addResourceHandler(resourceHandler).addResourceLocations("file:///"+filePath);
	}
}
