/**
 * FileName: UserService
 * Author:   小江
 * Date:     2019/12/26 21:12
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.User;

import java.util.List;

public interface UserService {

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/4 17:11
	*Description   通过userId查询数据库用户对象
	*/
	User findByUserId(String userId);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/4 17:11
	*Description   查询数据库所有用户对象
	*/
	List<User> findAllUser();

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/14 0:02
	*Description   插入数据库用户对象
	*/
	boolean saveUser(User user);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/26 23:12
	*Description  修改数据库用户对象
	*/
	User editUser(User user);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/26 23:12
	*Description  删除数据库用户对象
	*/
	int deleteUser(String userId);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/26 23:12
	*Description  给用户分配对应的角色
	*/
	User distributeRole(String userId,String roleName);
}