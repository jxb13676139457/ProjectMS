/**
 * FileName: RoleService
 * Author:   小江
 * Date:     2020/1/5 0:35
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.Role;

import java.util.List;

public interface RoleService {

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 17:12
	*Description  通过roleId查找Role对象
	*/
	Role findByRoleId(int roleId);

	/**
	 *@Author 小江  [com.zhbit]
	 *@Date 2020/1/4 21:21
	 *Description   查询数据库所有角色对象
	 */
	List<Role> findAllRole();

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/5 1:06
	*Description  插入数据库角色对象
	*/
	Role saveRole(Role role);
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/6 21:16
	*Description  修改数据库角色对象
	*/
	Role editRole(Role role);

	int deleteRole(int roleId);




}