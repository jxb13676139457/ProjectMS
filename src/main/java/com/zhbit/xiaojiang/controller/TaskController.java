/**
 * FileName: TaskController
 * Author:   小江
 * Date:     2020/2/16 17:49
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.zhbit.xiaojiang.entity.Project;
import com.zhbit.xiaojiang.entity.Task;
import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.service.ProjectService;
import com.zhbit.xiaojiang.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {

	//Log4j日志打印
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskService taskService;
	@Autowired
	private ProjectService projectService;

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/19 11:38
	*Description  查看当前登录用户的所有参与任务
	*/
	@GetMapping("/user-sys/tasks/{userId}")
	public String showTask(@PathVariable("userId") String userId,
	                       Model model,
	                       HttpSession session){
		List<Task> tasks = taskService.findAllTask(userId,session);
		model.addAttribute("tasks", tasks);
		return "user/taskList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/22 11:15
	*Description  跳转到添加任务界面
	*/
	@GetMapping("/user-sys/task")
	public String toAddTask(Model model){
		List<Project> projectList = projectService.findAllProject();
		model.addAttribute("projectList",projectList);
		return "user/addTask";
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/22 11:41
	*Description  添加任务
	*/
	@PostMapping("/user-sys/task")
	public String addTask(Task task, HttpSession session){
		boolean result = taskService.addTask(task,session);
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
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/4/4 23:09
	*Description  删除任务
	*/
	@DeleteMapping("/user-sys/task/{taskId}")
	@ResponseBody
	public int deleteTask(@PathVariable("taskId") String taskId) {
		int result = taskService.deleteTask(taskId);
		if (result == 1) {
			logger.info("Controller层删除任务成功");
		} else {
			logger.info("Controller层删除任务失败");
		}
		return result;
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/4/7 23:57
	*Description  查找当前任务可指派的项目成员
	*/
	@GetMapping("/user-sys/task-apport/{taskId}")
	@ResponseBody
	public List<User> showApportMembers(@PathVariable("taskId") String taskId
										,HttpSession session){
		//查找任务可指派的项目成员
		List<User> userList = taskService.findUserList(taskId);
		logger.info("测试："+userList);
		return userList;
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/4/9 18:55
	*Description  指派当前任务给项目成员,涉及mybatis多参数修改
	*/
	@PutMapping("/user-sys/task-apport/{taskId}")
	@ResponseBody
	public int apportTask(@PathVariable("taskId") String taskId
						,@RequestParam("userId") String userId
						,@RequestParam("taskType") String taskType) {
		logger.info("打印："+taskType);
		int result = taskService.apportTask(taskId,userId,taskType);
		if (result == 1) {
			logger.info("Controller层指派任务成功");
		} else {
			logger.info("Controller层指派任务失败");
		}
		return result;
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/5/6 1:51
	*Description  验收完成任务并自动计算项目进度
	*/
	@PutMapping("/user-sys/task-finish/{taskId}")
	@ResponseBody
	public int finishTask(@PathVariable("taskId") String taskId
			,@RequestParam("taskStatus") String taskStatus) {
		logger.info("打印："+taskStatus);
		int result = taskService.finishTask(taskId,taskStatus);
		//根据任务ID找到对应任务的项目ID
		Task task = taskService.findByTaskId(taskId);
		String projectId = task.getProjectId();
		//获取要更新进度的项目对象
		Project project = projectService.findByProjectId(projectId);
		if (result == 1) {
			logger.info("Controller层验收任务成功同时更新进度");
			float process = taskService.calculateProcess(projectId);
			//转换成百分比
			process = process*100;
			logger.info("计算到的进度："+process);
			project.setProcess(process);
			projectService.editProject(project);
			logger.info("修改后的进度："+project.getProcess());
			logger.info("运行到此处");
		} else {
			logger.info("Controller层验收任务失败");
		}
		return result;
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/5/23 23:48
	*Description  展示当前用户的处理任务数情况
	*/
	@GetMapping("/user-sys/index/{userId}")
	public String user(@PathVariable("userId") String userId,Model model){
		List<Integer> taskCount = taskService.taskCount(userId);
		logger.info("打印任务情况："+taskCount);
		model.addAttribute("taskCount",taskCount);
		return "user/index";
	}

}