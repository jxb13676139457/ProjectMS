/**
 * FileName: RoleMapper
 * Author:   小江
 * Date:     2020/1/5 0:33
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value ="roleMapper")
public interface RoleMapper {

	//通过roleId查找Role对象
	Role findByRoleId(int roleId);

	//查找全部Role对象
	List<Role> findAllRole();

	//插入Role对象
	int saveRole(Role role);

	//修改Role对象
	int editRole(Role role);

	//删除Role对象
	int deleteRole(int roleId);

	//通过角色名查找Role对象
	Role findByRoleName(String roleName);


}