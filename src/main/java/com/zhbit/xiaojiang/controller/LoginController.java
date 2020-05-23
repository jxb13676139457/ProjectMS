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
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	//Log4j日志打印
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

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
	public String login(String userId,String password,Model model,HttpSession session){

		//1、获取Subject对象，即当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//2、封装用户信息令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userId,password);
		logger.info("登录输入的密码："+token.getPassword());
		//3、执行login方法
		try{
			//进行验证，报错返回登录首页，不报错到达主页
			currentUser.login(token);
			User user = userService.findByUserId(userId);
			String roleType = user.getRole().getRoleType();
			String userName = user.getUserName();
			//用户名和角色类型存入session
			session.setAttribute("userId",userId);
			session.setAttribute("userName",userName);
			session.setAttribute("roleType",roleType);

			//判断到达前台主页还是后台主页
			if(roleType.equals("admin")){
				return "redirect:/admin-sys/projects";
			}else{
				return "redirect:/user-sys/projects/"+session.getAttribute("userId");
			}
		}catch (UnknownAccountException uae){
			model.addAttribute("msg","用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException ice){
			model.addAttribute("msg","密码错误");
			return "login";
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

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/8 15:35
	*Description  查看管理员具体信息
	*/
	@GetMapping("/admin-sys/admin/{userId}")
	public String showAdmin(@PathVariable("userId") String userId,Model model){
		logger.info("获取到的userID："+userId);
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "admin/showLoginerDetail";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/17 20:17
	*Description  查看前台用户具体信息
	*/
	@GetMapping("/user-sys/user/{userId}")
	public String showUser(@PathVariable("userId") String userId,Model model){
		logger.info("获取到的userID："+userId);
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "user/showLoginerDetail";
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/8 15:47
	*Description  用户未授权时跳转请求
	*/
	@RequestMapping("/noauth")
	@ResponseBody
	public String unauthorized(){
		return "未经授权不得访问此功能";
	}

	@RequestMapping("/user/add")
	public String add(){
		return "user/ui-elements";
	}

	@RequestMapping("/user/update")
	public String uodate(){
		return "user/ui-elements";
	}

	@RequestMapping("/user/chart")
	public String toChart(){
		return "user/chart";
	}

}