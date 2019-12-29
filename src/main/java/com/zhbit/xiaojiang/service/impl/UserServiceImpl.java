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

@Service
public class UserServiceImpl implements UserService {

	//自动注入userMapper接口
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUserId(String userId) {
		return userMapper.findByUserId(userId);
	}

}