/**
 * FileName: UserRealm
 * Author:   小江
 * Date:     2019/12/22 16:35
 * History:
 */
package com.zhbit.xiaojiang.component;

import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
*@Author 小江  [com.zhbit]
*@Date 2019/12/22 16:36
*Description  自定义Realm
*/
public class UserRealm extends AuthorizingRealm {

	//Log4j日志打印
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserRealm.class);

	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("执行授权方法");



		return null;
	}

	@Autowired
	private UserService userService;

	//执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		logger.info("执行认证方法");
		//加这一步的目的是在post请求时会先进入认证然后再到请求。
		if(authenticationToken.getPrincipal()==null){
			return null;
		}
		//从token获取用户ID,从主体传过来的认证信息中获取
		String userId = authenticationToken.getPrincipal().toString();
		//根据用户ID获得数据库用户对象
		User user = userService.findByUserId(userId);
		if(user==null){
			//此处返回null的话shiro底层会抛出UnknownAccountException异常
			return null;
		} else{
			//获取盐值，即用户ID
			ByteSource salt = ByteSource.Util.bytes(userId);
			String realmName = this.getName();
			//判断密码是否匹配
			//是AuthenticationInfo的子类，第一个参数是返回到login的一些数据，第二个参数是数据库密码，第三个参数是盐值，第四个参数是shiro的名称
			return new SimpleAuthenticationInfo(user,user.getPassword(),salt,realmName);
		}
	}


}