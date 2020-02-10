/**
 * FileName: ProjectMapper
 * Author:   小江
 * Date:     2020/2/4 16:27
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.Auditing;
import com.zhbit.xiaojiang.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "projectMapper")
public interface ProjectMapper {

	//查找所有已审核Project对象
	List<Project> findAllProject();

	//根据projectId查找Project对象
	Project findByProjectId(String projectId);

	//修改Project对象
	int editProject(Project project);

	//删除Project对象
	int deleteProject(String projectId);

	//删除Document对象
	int deleteDocument(String projectId);

	//查找所有未审核Project对象
	List<Auditing> findAllAuditing();

	//根据auditingId查找Auditing对象
	Auditing findByAuditingId(String auditingId);

	//修改Auditing对象
	int editAuditing(Auditing auditing);

	//删除Auditing对象
	int deleteAuditing(String auditingId);

	//插入Project对象
	int saveProject(Auditing auditing);

	//清空所有Auditing对象
	int deleteAllAuditing();


}