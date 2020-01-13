/**
 * FileName: RoleController
 * Author:   小江
 * Date:     2020/1/5 0:45
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.xiaojiang.entity.Role;
import com.zhbit.xiaojiang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 *@Author 小江  [com.zhbit]
	 *@Date 2020/1/4 21:40
	 *Description  查询所有Role对象
	 */
	@GetMapping("/admin-sys/roles")
	public String roleList(Model model,
	                       @RequestParam(required=false,defaultValue="1",value="pageNum")Integer pageNum,
	                       @RequestParam(required=false,defaultValue="5",value="pageSize")Integer pageSize){
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
		//System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

		PageHelper.startPage(pageNum,pageSize);
		try {
			List<Role> roleList = roleService.findAllRole();
			PageInfo<Role> rolePageInfo = new PageInfo<Role>(roleList, pageSize);
			model.addAttribute("roleList", roleList);
			model.addAttribute("rolePageInfo", rolePageInfo);
		}finally {
			PageHelper.clearPage();
		}
		return "admin/roleList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 15:52
	*Description   跳转到添加页面
	*/
	@GetMapping("/admin-sys/role")
	public String toAddRole(){
		return "admin/addRole";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 1:40
	*Description   添加Role对象，只要表单内的input标签的name属性和entity实体类的属性名对应上
	 *             springMVC就可以将请求参数和入参对象的属性一一绑定自动封装成对象
	*/
	@PostMapping("/admin-sys/role")
	public String addRole(Role role){
		System.out.println("保存的角色信息："+role);
		roleService.saveRole(role);
		return "redirect:/admin-sys/roles";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 15:54
	*Description   跳转到修改页面，查出当前角色，在编辑页面回显
	*/
	@GetMapping("/admin-sys/role-detail/{roleId}")
	public String toUpdateRole(@PathVariable("roleId") Integer roleId,Model model){
		Role role = roleService.findByRoleId(roleId);
		model.addAttribute("role",role);
		return "admin/showRoleDetail";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 15:52
	*Description  更新指定的Role对象数据并持久化到数据库
	*/
	@PutMapping("/admin-sys/role")
	public String updateRole(Role role){
		roleService.editRole(role);
		return "redirect:/admin-sys/roles";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/13 23:51
	*Description  删除指定的Role对象
	*/
	@DeleteMapping("admin-sys/role/{roleId}")
	@ResponseBody
	public int deleteRole(@PathVariable("roleId") Integer roleId){
		System.out.println("进入后台删除操作:"+roleId);
		int result = roleService.deleteRole(roleId);
		if(result==1){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		return result;
	}



}