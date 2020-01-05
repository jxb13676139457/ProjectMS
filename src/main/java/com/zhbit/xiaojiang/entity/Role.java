/**
 * FileName: Role
 * Author:   小江
 * Date:     2019/12/29 16:04
 * History:
 */
package com.zhbit.xiaojiang.entity;

public class Role {
	private int roleId;
	private String roleType;
	private String roleName;
	private String rolePower;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRolePower() {
		return rolePower;
	}

	public void setRolePower(String rolePower) {
		this.rolePower = rolePower;
	}
}