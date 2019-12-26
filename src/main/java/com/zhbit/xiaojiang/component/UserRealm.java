/**
 * FileName: UserRealm
 * Author:   小江
 * Date:     2019/12/22 16:35
 * History:
 */
package com.zhbit.xiaojiang.component;

import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
*@Author 小江  [com.zhbit]
*@Date 2019/12/22 16:36
*Description  自定义Realm
*/
public class UserRealm extends AuthorizingRealm {

	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行授权方法");
		return null;
	}

	@Autowired
	private UserService userService;

	//执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("执行认证方法");

		//Shiro编写判断用户名和密码逻辑
		//1、判断用户名是否匹配
		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		//获得数据库用户对象
		User user = userService.findByUserName(token.getUsername());

		if(user==null){
			//此处返回null的话shiro底层会抛出UnknownAccountException异常
			return null;
		}
		//2、判断密码是否匹配
		//是AuthenticationInfo的子类，第一个参数是返回到login的一些数据，第二个参数是数据库密码，第三个参数是shiro的名称
		return new SimpleAuthenticationInfo("",token.getPassword(),"");
	}
}