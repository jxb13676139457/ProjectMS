/**
 * FileName: UserMapper
 * Author:   小江
 * Date:     2019/12/26 21:07
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.User;

public interface UserMapper {

	User findByUserName(String userName);

}