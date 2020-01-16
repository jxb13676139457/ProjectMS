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
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
	public boolean saveUser(User user) {
		//将用户名作为盐值
		ByteSource salt = ByteSource.Util.bytes(user.getUserName());
		//加密后的密码
		String encryptPassword = new SimpleHash("MD5",user.getPassword(),salt,2).toHex();
		System.out.println(encryptPassword);
		user.setPassword(encryptPassword);
		//先查询数据库看是否有相同用户
		User isExistUser = userMapper.findByUserId(user.getUserId());
		if(isExistUser==null){
			userMapper.saveUser(user);
			return true;
		} else{
			return false;
		}

	}

	@Override
	public User editUser(User user) {
		userMapper.editUser(user);
		return user;
	}

	public int deleteUser(String userId){
		userMapper.deleteUser(userId);
		return 1;
	}


}