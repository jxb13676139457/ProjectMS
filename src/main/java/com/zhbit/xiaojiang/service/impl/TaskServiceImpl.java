/**
 * FileName: TaskServiceImpl
 * Author:   小江
 * Date:     2020/2/16 17:50
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Execute;
import com.zhbit.xiaojiang.entity.Task;
import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.mapper.TaskMapper;
import com.zhbit.xiaojiang.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Autowired
	private TaskMapper taskMapper;

	@Override
	public List<Task> findAllTask(String userId, HttpSession session) {
		List<Task> taskList = taskMapper.findAllTask(userId);
		//从session中获取当前登录对象
		String roleType = session.getAttribute("roleType").toString();
		//比对是不是项目负责人权限，是的话则不合入需求任务
		if(("管理").equals(roleType)){
			return taskList;
		}else{
			List<Task> demandTasks = taskMapper.findAllDemand(userId);
			logger.info("当前用户所参与的项目需求："+demandTasks);
			//合入所有需求任务，以便非项目负责人登录时可看到所参与项目的所有需求
			taskList.addAll(demandTasks);
			logger.info("合入后的总任务数："+taskList.size());
			return taskList;
		}
	}

	@Override
	public Task findByTaskId(String taskId) {
		return taskMapper.findByTaskId(taskId);
	}

	@Override
	public boolean addTask(Task task,HttpSession session) {
		//把execute的具体数据封装成对象
		Execute execute = new Execute();
		execute.setUserId(session.getAttribute("userId").toString());
		execute.setTaskId(task.getTaskId());
		if(taskMapper.saveTask(task)>0){
			//添加需求任务的同时把任务挂在创建者身上
			taskMapper.handExecute(execute);
			logger.info("插入任务成功");
			return true;
		}else{
			logger.info("插入任务失败");
			return false;
		}

	}

	@Override
	public int deleteTask(String taskId) {
		//删除主表
		if(taskMapper.deleteTask(taskId)==1){
			//级联删除关联的子表数据
			taskMapper.deleteExecute(taskId);
			logger.info("ServiceImpl层级联删除任务成功");
			return 1;
		}else{
			logger.info("ServiceImpl层级联删除任务失败");
			return 0;
		}
	}

	@Override
	public List<User> findUserList(String taskId) {
		return taskMapper.findUserList(taskId);
	}

	@Override
	public int apportTask(String taskId,String userId,String taskType) {
		if(taskMapper.apportTask(taskId,userId)==1){
			//同时修改任务为正在执行
			taskMapper.editTask(taskId,taskType);
			logger.info("ServiceImpl层级指派任务成功");
			return 1;
		}else{
			logger.info("ServiceImpl层级联指派任务失败");
			return 0;
		}
	}

	@Override
	public int finishTask(String taskId, String taskStatus) {
		int result = taskMapper.finishTask(taskId,taskStatus);
		return result;
	}

	@Override
	public float calculateProcess(String projectId) {


		float process = taskMapper.calculateProcess(projectId);
		logger.info("进度："+process);
		return process;
	}

	@Override
	public List<Integer> taskCount(String userId) {
		int yesCount = taskMapper.yesCount(userId);
		int allCount = taskMapper.allCount(userId);
		logger.info("任务数情况："+yesCount+","+allCount);
		List<Integer> list = new ArrayList<>();
		list.add(yesCount);
		list.add(allCount);
		return list;
	}

}