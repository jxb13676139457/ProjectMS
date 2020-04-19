/**
 * FileName: TaskService
 * Author:   小江
 * Date:     2020/2/16 17:50
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.Task;
import com.zhbit.xiaojiang.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TaskService {

	//查找所有task对象
	List<Task> findAllTask(String userId, HttpSession session);

	//根据指定taskId查看当前task对象
	Task findByTaskId(String taskId);

	//添加task对象
	boolean addTask(Task task,HttpSession session);

	//删除task对象
	int deleteTask(String taskId);

	//查找任务可指派的项目成员
	List<User> findUserList(String taskId);

	//指派任务给项目成员
	int apportTask(String taskId,String userId);

}