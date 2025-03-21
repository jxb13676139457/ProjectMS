/**
 * FileName: ProjectServiceImpl
 * Author:   小江
 * Date:     2020/2/4 16:23
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Auditing;
import com.zhbit.xiaojiang.entity.Members;
import com.zhbit.xiaojiang.entity.Project;
import com.zhbit.xiaojiang.mapper.ProjectMapper;
import com.zhbit.xiaojiang.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

	//获取Log4j日志打印
	Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> findAllProject() {
		return projectMapper.findAllProject();
	}

	@Override
	public Project findByProjectId(String projectId) {
		logger.info("获取到的projectId："+projectId);
		return projectMapper.findByProjectId(projectId);
	}

	@Override
	@Transactional
	public int editProject(Project project) {
		int result =projectMapper.editProject(project);
		if(result>0){
			logger.info("修改成功");
		}else{
			logger.info("修改失败");
		}
		return result;
	}

	@Override
	@Transactional
	public int deleteProject(String projectId) {
		//删除主表
		if(projectMapper.deleteProject(projectId)==1){
			//级联删除关联的子表数据
			projectMapper.deleteDocument(projectId);
			logger.info("删除项目成功");
			return 1;
		}else{
			logger.info("删除项目失败");
			return 0;
		}
	}

	@Override
	public List<Auditing> findAllAuditing() {
		return projectMapper.findAllAuditing();
	}

	@Override
	public Auditing findByAuditingId(String auditingId) {
		logger.info("获取到的auditingId："+auditingId);
		return projectMapper.findByAuditingId(auditingId);
	}

	@Override
	@Transactional
	public Auditing editAuditing(Auditing auditing) {
		String auditingId = auditing.getAuditingId();
		int auditinigStatus = auditing.getAuditingStatus();
		String leader = auditing.getLeader_tmp();
		//根据项目负责人名字找到用户ID
		String userId = projectMapper.findByUserName(leader).getUserId();
		int result = projectMapper.editAuditing(auditing);
		if(auditinigStatus==0){
			logger.info("管理员选择拒绝通过审核");
		}else if(auditinigStatus==1){
			if(result==1){
				logger.info("获取被修改项目的id："+auditingId);
				//先查询数据库看是否有相同项目，有的话不允许重复插入
				Project isExistProject = projectMapper.findByProjectId(auditingId);
				if(isExistProject==null){
					projectMapper.saveProject(auditing);
					logger.info("同步到真正项目表成功");
					//同时把项目挂到负责人ID名下
					projectMapper.saveMember(userId,auditingId);
					//审核通过同步后直接在未审核项目表删除记录并插入真正项目表
					projectMapper.deleteAuditing(auditingId);
				} else{
					logger.info("有重复项目,同步失败");
				}
			}else {
				logger.info("修改操作失败");
			}
		}
		return auditing;
	}

	@Override
	public int deleteAllAuditing() {
		int result = projectMapper.deleteAllAuditing();
		return result;
	}

	@Override
	public List<Project> findByUserId(String userId) {
		List<Project> projects = projectMapper.findByUserId(userId);
		return projects;
	}

	@Override
	public List<Project> searchKeyword(String keyword) {
		return projectMapper.findByKeyword(keyword);
	}

	@Override
	public List<Members> findAllMember(String projectId) {
		return projectMapper.findAllMember(projectId);
	}

	@Override
	public int saveMember(String userId, String projectId) {
		return projectMapper.saveMember(userId,projectId);
	}

	@Override
	public int deleteMember(int memberId) {
		return projectMapper.deleteMember(memberId);
	}

	@Override
	public int saveAuditing(Auditing auditing) {
		int result = projectMapper.saveAuditing(auditing);
		if(result>0){
			logger.info("立项成功");
		}else{
			logger.info("立项失败");
		}
		return result;
	}

}