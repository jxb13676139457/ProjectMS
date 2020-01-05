/**
 * FileName: RoleServiceImpl
 * Author:   小江
 * Date:     2020/1/5 0:36
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Role;
import com.zhbit.xiaojiang.mapper.RoleMapper;
import com.zhbit.xiaojiang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	//自动注入roleMapper对象
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role findByRoleId(int roleId) {
		return roleMapper.findByRoleId(roleId);
	}

	@Override
	public List<Role> findAllRole() {
		return roleMapper.findAllRole();
	}

	//插入时，只要表单内的input标签的name属性和entity实体类的属性名对应上就可以一一绑定自动封装成对象
	@Override
	public Role saveRole(Role role) {
		roleMapper.saveRole(role);
		return role;
	}
}