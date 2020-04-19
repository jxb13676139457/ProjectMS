/**
 * FileName: TaskMapper
 * Author:   小江
 * Date:     2020/2/16 17:51
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.Execute;
import com.zhbit.xiaojiang.entity.Task;
import com.zhbit.xiaojiang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "taskMapper")
public interface TaskMapper {

	//查找当前用户的所有任务列表
	List<Task> findAllTask(String userId);

	//根据taskId查看当前任务详情
	Task findByTaskId(String taskId);

	//添加task对象
	int saveTask(Task task);

	//添加需求任务的同时把任务挂在创建者身上
	int handExecute(Execute execute);

	//查询当前用户的所属项目的所有需求
	List<Task> findAllDemand(String userId);

	//删除task对象
	int deleteTask(String taskId);

	//删除任务执行者记录
	int deleteExecute(String taskId);

	//查找任务可指派的项目成员
	List<User> findUserList(String taskId);

	//指派任务给项目成员
	int apportTask(@Param("taskId") String taskId, @Param("userId") String userId);

	//修改指定任务的状态和类型
	int editDemandTask(String taskId);



}