/**
 * FileName: ProjectService
 * Author:   小江
 * Date:     2020/2/4 16:23
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.Auditing;
import com.zhbit.xiaojiang.entity.Members;
import com.zhbit.xiaojiang.entity.Project;

import java.util.List;

public interface ProjectService {

	//查找所有已审核project对象
	List<Project> findAllProject();

	//根据projectId查找Project对象
	Project findByProjectId(String projectId);

	//修改Project对象
	int editProject(Project project);

	//删除Projec对象
	int deleteProject(String projectId);

	//查找所有未审核project对象
	List<Auditing> findAllAuditing();

	//根据auditingId查找Auditing对象
	Auditing findByAuditingId(String auditingId);

	//修改Auditing对象
	Auditing editAuditing(Auditing auditing);

	//清空所有Auditing对象
	int deleteAllAuditing();

	//查找当前登录用户的参与项目
	List<Project> findByUserId(String userId);

	//按关键字搜索项目
	List<Project> searchKeyword(String keyword);

	//查找当前项目的所有项目成员
	List<Members> findAllMember(String projectId);

	//添加项目成员
	int saveMember(String userId,String projectId);

	//删除指定项目成员
	int deleteMember(int memberId);

	//立项
	int saveAuditing(Auditing auditing);


}