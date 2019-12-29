/**
 * FileName: LoginController
 * Author:   小江
 * Date:     2019/12/17 22:43
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.zhbit.xiaojiang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/24 22:30
	*Description  登录页面访问请求
	*/
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/24 22:30
	*Description  前台用户首页请求
	*/
	@RequestMapping("/user")
	public String user(){
		return "/user/index";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/24 22:31
	*Description  后台管理员首页请求
	*/
	@RequestMapping("/admin")
	public String admin(){
		return "/admin/index";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/24 22:31
	*Description  登录请求，用Shiro完成用户认证操作
	*/
	@RequestMapping("/login")
	public String login(String userId,String password,Model model){

		//1、获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		//2、封装用户信息令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userId,password);
		//3、执行login方法
		try{
			//进行验证，报错返回首页，不报错到达主页
			subject.login(token);
			//判断到达前台主页还是后台主页
			if(userService.findByUserId(userId).getRole().getRoleType()==1){
				return "redirect:/admin";
			}else{
				return "redirect:/user";
			}
		}catch (UnknownAccountException uae){
			model.addAttribute("msg","用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException ice){
			model.addAttribute("msg","密码错误");
			return"login";
		}
	}

}