/**
 * FileName: LoginController
 * Author:   小江
 * Date:     2019/12/17 22:43
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	*Description  跳转到前台用户首页
	*/
	@RequestMapping("/user-sys/index")
	public String user(){
		return "user/index";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/24 22:31
	*Description  跳转到后台管理员首页
	*/
	@RequestMapping("/admin-sys/index")
	public String admin(){
		return "admin/index";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2019/12/24 22:31
	*Description  登录请求，用Shiro完成用户认证操作
	*/
	@RequestMapping("/login")
	public String login(String userId,String password,Model model){

		//1、获取Subject对象，即当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//2、封装用户信息令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userId,password);
		System.out.println("登录输入的密码："+token.getPassword());
		//3、执行login方法
		try{
			//进行验证，报错返回登录首页，不报错到达主页
			currentUser.login(token);
			User user = userService.findByUserId(userId);
			String roleType = user.getRole().getRoleType();
			String userName = user.getUserName();
			Session session = currentUser.getSession();
			//用户名和角色类型存入session
			//model.addAttribute("userId",userId);
			session.setAttribute("userId",userId);
			session.setAttribute("userName",userName);
			session.setAttribute("roleType",roleType);

			//判断到达前台主页还是后台主页
			if(roleType.equals("admin")){
				return "/admin/index";
			}else{
				return "/user/index";
			}
		}catch (UnknownAccountException uae){
			model.addAttribute("msg","用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException ice){
			model.addAttribute("msg","密码错误");
			return"login";
		}
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 22:44
	*Description   登出请求，用Shiro完成登出拦截器
	*/
	@RequestMapping("/auth/logout")
	public String logout(){
		return "redirect:/toLogin";
	}

	@GetMapping("/admin-sys/loginer/{userId}")
	public String showLoginer(@PathVariable("userId") String userId,Model model){
		System.out.println("获取到的userID："+userId);
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "admin/showLoginerDetail";

	}

}