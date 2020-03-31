/**
 * FileName: TaskServiceImpl
 * Author:   小江
 * Date:     2020/2/16 17:50
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Execute;
import com.zhbit.xiaojiang.entity.Task;
import com.zhbit.xiaojiang.mapper.TaskMapper;
import com.zhbit.xiaojiang.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Autowired
	private TaskMapper taskMapper;

	@Override
	public List<Task> findAllTask(String userId) {
		return taskMapper.findAllTask(userId);
	}

	@Override
	public Task findByTaskId(String taskId) {
		return taskMapper.findByTaskId(taskId);
	}

	@Override
	public boolean addTask(Task task, HttpSession session) {
		//把execute的具体数据封装成对象
		Execute execute = new Execute();
		execute.setUserId(session.getAttribute("userId").toString());
		execute.setTaskId(task.getTaskId());
		if(taskMapper.saveTask(task)>0){
			//添加需求任务的同时把任务挂在创建者身上
			taskMapper.handExecute(execute);
			return true;
		}else{
			logger.info("插入任务失败");
			return false;
		}

	}
}