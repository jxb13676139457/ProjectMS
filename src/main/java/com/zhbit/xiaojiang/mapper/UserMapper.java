/**
 * FileName: UserMapper
 * Author:   小江
 * Date:     2019/12/26 21:07
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.Role;
import com.zhbit.xiaojiang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userMapper")
public interface UserMapper {

	//通过userId查找User对象
	User findByUserId(String userId);

	//查找全部User对象
	List<User> findAllUser();

	//插入User对象
	int saveUser(User user);

	//修改User对象
	int editUser(User user);

	//删除User对象
	int deleteUser(String userId);

	//根据角色岗位查询Role对象
	Role findByRoleName(String roleName);

	//分配用户角色
	int distributeRole(User user);

	//按关键字搜索用户
	List<User> findByKeyword(String keyword);

}