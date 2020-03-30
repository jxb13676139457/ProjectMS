/**
 * FileName: TaskController
 * Author:   小江
 * Date:     2020/2/16 17:49
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.zhbit.xiaojiang.entity.Task;
import com.zhbit.xiaojiang.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {

	//Log4j日志打印
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskService taskService;

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/19 11:38
	*Description  查看当前登录用户的所有参与任务
	*/
	@GetMapping("/user-sys/tasks/{userId}")
	public String showTask(@PathVariable("userId") String userId,Model model){
		List<Task> tasks = taskService.findAllTask(userId);
		model.addAttribute("tasks", tasks);
		return "user/taskList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/22 11:15
	*Description  跳转到添加任务界面
	*/
	@GetMapping("/user-sys/task")
	public String toAddTask(){
		return "user/addTask";
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/22 11:41
	*Description  添加任务
	*/
	@PostMapping("/user-sys/task")
	public String addTask(Task task, HttpSession session){
		boolean result = taskService.addTask(task);
		if(result==true){
			logger.info("添加成功");
		}else{
			logger.info("添加失败");
		}
		return "redirect:/user-sys/tasks/"+session.getAttribute("userId");

	}


	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/19 11:39
	*Description  跳转到查看当前任务详情界面
	*/
	@GetMapping("/user-sys/task/{taskId}")
	public String showTaskDetail(@PathVariable("taskId") String taskId,Model model){
		Task task = taskService.findByTaskId(taskId);
		model.addAttribute("task",task);
		return "user/showTaskDetail";
	}

}