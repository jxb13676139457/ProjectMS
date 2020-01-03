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

	User findByUserId(String userId);

	List<User> findAllUser();



}