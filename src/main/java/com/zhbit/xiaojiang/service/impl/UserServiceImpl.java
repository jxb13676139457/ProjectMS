/**
 * FileName: UserServiceImpl
 * Author:   小江
 * Date:     2019/12/26 21:12
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.mapper.UserMapper;
import com.zhbit.xiaojiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	//自动注入userMapper接口
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUserId(String userId) {
		return userMapper.findByUserId(userId);
	}

	@Override
	public List<User> findAllUser() {

		return userMapper.findAllUser();
	}

	//插入时，只要表单内的input标签的name属性和entity实体类的属性名对应上就可以一一绑定自动封装成对象
	@Override
	public User saveUser(User user) {
		userMapper.saveUser(user);
		return user;
	}

}