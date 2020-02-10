/**
 * FileName: Members
 * Author:   小江
 * Date:     2020/2/4 16:18
 * History:
 */
package com.zhbit.xiaojiang.entity;

public class Members {

	private int memberId;

	private User user;
	private Project project;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}