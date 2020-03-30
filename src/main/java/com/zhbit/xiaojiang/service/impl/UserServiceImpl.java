/**
 * FileName: UserServiceImpl
 * Author:   小江
 * Date:     2019/12/26 21:12
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Role;
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
		//将用户ID作为盐值
		ByteSource salt = ByteSource.Util.bytes(user.getUserId());
		//加密后的密码
		String encryptPassword = new SimpleHash("MD5",user.getPassword(),salt,2).toHex();
		user.setPassword(encryptPassword);
		//先查询数据库看是否有相同用户，有的话不允许重复插入
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
		//将用户ID作为盐值
		ByteSource salt = ByteSource.Util.bytes(user.getUserId());
		//加密密码
		String encryptPassword = new SimpleHash("MD5",user.getPassword(),salt,2).toHex();
		//修改的密码进入数据库前对应也要完成加密操作
		user.setPassword(encryptPassword);
		userMapper.editUser(user);
		return user;
	}

	@Override
	public int deleteUser(String userId){
		userMapper.deleteUser(userId);
		return 1;
	}

	@Override
	public User distributeRole(String userId,String roleName) {
		User user = findByUserId(userId);
		Role role = userMapper.findByRoleName(roleName);
		int roleId = role.getRoleId();
		user.setRoleId(roleId);
		userMapper.distributeRole(user);
		return user;
	}

	@Override
	public List<User> searchKeyword(String keyword) {
		return userMapper.findByKeyword(keyword);
	}


}