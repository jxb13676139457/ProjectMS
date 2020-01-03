/**
 * FileName: UserMapper
 * Author:   小江
 * Date:     2019/12/26 21:07
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value ="userMapper")
public interface UserMapper {

	//通过userId查找User对象
	User findByUserId(String userId);

	//查找全部User对象
	List<User> findAllUser();

}