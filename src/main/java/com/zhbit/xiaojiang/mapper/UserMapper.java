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

@Mapper
@Component(value ="userMapper")
public interface UserMapper {

	User findByUserName(String userName);

}