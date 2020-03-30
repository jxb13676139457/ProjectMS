/**
 * FileName: TaskMapper
 * Author:   小江
 * Date:     2020/2/16 17:51
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.Task;
import org.apache.ibatis.annotations.Mapper;
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

}