package com.zhbit.xiaojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
    *@Author 小江  [com.zhbit]
    *@Date 2020/1/4 21:41
    *Description  查询所有User对象
    */
    @RequestMapping("/admin-sys/users")
    public String userList(Model model,
                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="2",value="pageSize")Integer pageSize){
	    /**
	    *@Author 小江  [com.zhbit]
	    *@Date 2020/1/4 21:27
	    *Description   为了程序的严谨性，判断非空：
	    */
	    if(pageNum==null || pageNum<=0){
		    //设置默认当前页
		    pageNum = 1;
	    }
	    if(pageSize == null){
		    //设置默认每页显示的数据数
		    pageSize = 1;
	    }
	    System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

	    //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
	    PageHelper.startPage(pageNum,pageSize);
	    //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
	    try {
		    List<User> userList = userService.findAllUser();
		    System.out.println("分页数据："+userList);
		    //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
		    PageInfo<User> userPageInfo = new PageInfo<User>(userList,pageSize);
		    //4.使用model传参数回前端
		    model.addAttribute("userPageInfo",userPageInfo);
		    model.addAttribute("userList",userList);
	    }finally {
		    //清理 ThreadLocal 存储的分页参数,保证线程安全
		    PageHelper.clearPage();
	    }
        return "admin/userList";
    }

    /**
    *@Author 小江  [com.zhbit]
    *@Date 2020/1/14 0:03
    *Description   跳转到添加页面
    */
    @GetMapping("/admin-sys/user")
    public String toAddUser(){
    	return "admin-sys/addUser";
    }

    /**
    *@Author 小江  [com.zhbit]
    *@Date 2020/1/14 0:03
    *Description   添加User对象
    */
	@PostMapping("/admin-sys/user")
	public String addRole(User user){
		System.out.println("保存的用户信息："+user);
		userService.saveUser(user);
		return "redirect:/admin-sys/users";
	}

}
