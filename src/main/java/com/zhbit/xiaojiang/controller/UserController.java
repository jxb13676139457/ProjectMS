package com.zhbit.xiaojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.xiaojiang.entity.Role;
import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.RoleService;
import com.zhbit.xiaojiang.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
    *@Author 小江  [com.zhbit]
    *@Date 2020/1/4 21:41
    *Description  查询所有User对象
    */
    @RequestMapping("/admin-sys/users")
    public String userList(Model model,
                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
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
    	return "admin/addUser";
    }

    /**
    *@Author 小江  [com.zhbit]
    *@Date 2020/1/14 0:03
    *Description   添加User对象
    */
	@PostMapping("/admin-sys/user")
	public String addRole(User user, HttpServletRequest request){
		//创建session对象来存放交互结果
		HttpSession session = request.getSession();
		boolean result = userService.saveUser(user);
		if(result==true){
			System.out.println("添加成功");
			session.setAttribute("msg","添加用户成功");
		}else{
			System.out.println("添加失败");
			session.setAttribute("msg","添加用户失败,可能是用户ID已存在");
		}
		return "redirect:/admin-sys/users";
	}

	@GetMapping("/admin-sys/user-detail/{userId}")
	public String toUpdateUser(@PathVariable("userId") String userId,Model model){
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "admin/showUserDetail";
	}

	@PutMapping("/admin-sys/user")
	public String updateUser(User user){
		userService.editUser(user);
		return "redirect:/admin-sys/users";
	}

	@DeleteMapping("/admin-sys/user/{userId}")
	@ResponseBody
	public int deleteUser(@PathVariable("userId") String userId){
		System.out.println("进入后台删除操作:"+userId);
		int result = userService.deleteUser(userId);
		if(result==1){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		return result;
	}

	@GetMapping("/admin-sys/user/role")
	public String userRoleList(Model model,
								 @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
								 @RequestParam(defaultValue="8",value="pageSize")Integer pageSize){
		//分页查询所有用户对象
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
			//查询所有角色对象
			List<Role> roleList =  roleService.findAllRole();
			//System.out.println("测试角色名："+roleList.get(1).getRoleName());
			model.addAttribute("roleList",roleList);
			//4.使用model传参数回前端
			model.addAttribute("userPageInfo",userPageInfo);
			model.addAttribute("userList",userList);

		}finally {
			//清理 ThreadLocal 存储的分页参数,保证线程安全
			PageHelper.clearPage();
		}
		return "admin/userRole";
	}

	@PutMapping("/admin-sys/user/role/{userId},{roleName}")
	@ResponseBody
	public String distributeRole(@PathVariable("userId") String userId,
								 @PathVariable("roleName")String roleName){
		System.out.println("测试是否成功传参："+userId+roleName);
		User result = userService.distributeRole(userId,roleName);
		if(result!=null){
			System.out.println("分配成功");
		}else{
			System.out.println("分配失败");
		}
		return roleName;
	}

}
