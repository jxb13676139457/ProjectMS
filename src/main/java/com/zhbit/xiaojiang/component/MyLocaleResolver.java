/**
 * FileName: MyLocaleResolver
 * Author:   小江
 * Date:     2019/12/18 22:49
 * History:
 */
package com.zhbit.xiaojiang.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
*@Author 小江  [com.zhbit]
*@Date 2019/12/22 16:37
*Description  自定义区域信息解析器
*/
public class MyLocaleResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest httpServletRequest) {

		String str = httpServletRequest.getParameter("l");
		//初始化先使用默认的区域信息
		Locale locale = Locale.getDefault();
		if(!StringUtils.isEmpty(str)){
			String[] split = str.split("_");
			//根据传来的区域信息创建一个locale对象
			locale = new Locale(split[0],split[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

	}
}