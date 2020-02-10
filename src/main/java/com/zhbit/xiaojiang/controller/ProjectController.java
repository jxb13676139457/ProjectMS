/**
 * FileName: ProjectController
 * Author:   小江
 * Date:     2020/2/4 16:22
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.xiaojiang.entity.Auditing;
import com.zhbit.xiaojiang.entity.Project;
import com.zhbit.xiaojiang.service.ExcelService;
import com.zhbit.xiaojiang.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProjectController {

	//Log4j日志打印
	Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ExcelService excelService;

	@GetMapping("/admin-sys/projects")
	public String projectList(Model model,
	                          @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
	                          @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize) {
		/**
		 *@Author 小江  [com.zhbit]
		 *@Date 2020/2/4 17:10
		 *Description  为了程序的严谨性，判断非空：
		 */
		if (pageNum == null || pageNum <= 0) {
			//设置默认当前页
			pageNum = 1;
		}
		if (pageSize == null) {
			//设置默认每页显示的数据数
			pageSize = 1;
		}
		logger.info("当前页是：" + pageNum + "显示条数是：" + pageSize);

		//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
		PageHelper.startPage(pageNum, pageSize);
		//2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
		try {
			List<Project> projectList = projectService.findAllProject();
			logger.info("分页数据：" + projectList);
			//3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
			PageInfo<Project> projectPageInfo = new PageInfo<Project>(projectList, pageSize);
			//4.使用model传参数回前端
			model.addAttribute("projectPageInfo", projectPageInfo);
			model.addAttribute("projectList", projectList);
		} finally {
			//清理 ThreadLocal 存储的分页参数,保证线程安全
			PageHelper.clearPage();
		}
		return "admin/projectList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/6 23:14
	*Description  跳转到修改项目界面
	*/
	@GetMapping("/admin-sys/project/{projectId}")
	public String toUpdateProject(@PathVariable("projectId") String projectId, Model model) {
		Project project = projectService.findByProjectId(projectId);
		model.addAttribute("project", project);
		return "admin/showProjectDetail";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/6 23:13
	*Description  修改Project对象
	*/
	@PutMapping("/admin-sys/project")
	public String updateProject(Project project) {
		projectService.editProject(project);
		return "redirect:/admin-sys/projects";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/6 23:13
	*Description  删除Project对象，涉及自定义级联删除效果
	*/
	@DeleteMapping("/admin-sys/project/{projectId}")
	@ResponseBody
	public int deleteProject(@PathVariable("projectId") String projectId) {
		int result = projectService.deleteProject(projectId);
		if (result == 1) {
			logger.info("删除项目信息成功");
		} else {
			logger.info("删除项目信息失败");
		}
		return result;
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/8 23:03
	*Description  批量导出所有项目信息
	*/
	@GetMapping("/admin-sys/projects-export")
	public String exportProject(HttpServletResponse response){
		excelService.exportProject(response);
		return "redirect:/admin-sys/projects";
	}
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/8 23:02
	*Description  显示所有未审核的立项消息
	*/
	@GetMapping("/admin-sys/auditings")
	public String showAudits(Model model){
		List<Auditing> auditingList = projectService.findAllAuditing();
		model.addAttribute("auditingList",auditingList);
		return "admin/auditingList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/9 23:14
	*Description  跳转到查看某个未审核项目的具体信息页面
	*/
	@GetMapping("/admin-sys/auditing/{auditingId}")
	public String toUpdateAuditing(@PathVariable("auditingId") String auditingId,Model model){
		Auditing auditing = projectService.findByAuditingId(auditingId);
		model.addAttribute("auditing", auditing);
		return "admin/showAuditingDetail";

	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/9 23:15
	*Description  修改Auditing对象
	*/
	@PutMapping("/admin-sys/auditing")
	public String editAuditing(Auditing auditing){
		projectService.editAuditing(auditing);
		return "redirect:/admin-sys/auditings";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/10 16:34
	*Description  一键清空所有未审核项目
	*/
	@DeleteMapping("/admin-sys/auditing")
	@ResponseBody
	public int deleteAllAuditing() {
		int result = projectService.deleteAllAuditing();
		if (result>1) {
			logger.info("一键清空未审核项目成功");
		} else {
			logger.info("一键清空未审核项目失败");
		}
		return result;
	}

}
