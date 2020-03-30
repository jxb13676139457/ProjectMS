/**
 * FileName: TaskService
 * Author:   小江
 * Date:     2020/2/16 17:50
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.Task;

import java.util.List;

public interface TaskService {

	//查找所有task对象
	List<Task> findAllTask(String userId);

	//根据指定taskId查看当前task对象
	Task findByTaskId(String taskId);

	//添加task对象
	boolean addTask(Task task);

}