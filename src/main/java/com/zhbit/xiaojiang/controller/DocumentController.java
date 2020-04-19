/**
 * FileName: DocumentController
 * Author:   小江
 * Date:     2020/2/5 18:05
 * History:
 */
package com.zhbit.xiaojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.xiaojiang.entity.Document;
import com.zhbit.xiaojiang.entity.Project;
import com.zhbit.xiaojiang.service.DocumentService;
import com.zhbit.xiaojiang.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DocumentController {

	//Log4j日志打印
	Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;
	@Autowired
	private ProjectService projectService;

	@GetMapping("/admin-sys/documents")
	public String documentList(Model model,
	                          @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
	                          @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
		/**
		 *@Author 小江  [com.zhbit]
		 *@Date 2020/2/4 17:10
		 *Description  为了程序的严谨性，判断非空：
		 */
		if(pageNum==null || pageNum<=0){
			//设置默认当前页
			pageNum = 1;
		}
		if(pageSize == null){
			//设置默认每页显示的数据数
			pageSize = 1;
		}
		logger.info("当前页是："+pageNum+"显示条数是："+pageSize);

		//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
		PageHelper.startPage(pageNum,pageSize);
		//2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
		try {
			List<Document> documentList = documentService.findAllDocument();
			logger.info("分页数据："+documentList);
			//3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
			PageInfo<Document> documentPageInfo = new PageInfo<Document>(documentList,pageSize);
			//4.使用model传参数回前端
			model.addAttribute("documentPageInfo",documentPageInfo);
			model.addAttribute("documentList",documentList);
		}finally {
			//清理 ThreadLocal 存储的分页参数,保证线程安全
			PageHelper.clearPage();
		}
		return "admin/documentList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/4/4 13:23
	*Description  前台文档中心
	*/
	@GetMapping("/user-sys/documents/{userId}")
	public String document_user(Model model,
	                           @PathVariable("userId") String userId,
	                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
	                           @RequestParam(defaultValue="10",value="pageSize")Integer pageSize){
		/**
		 *@Author 小江  [com.zhbit]
		 *@Date 2020/2/4 17:10
		 *Description  为了程序的严谨性，判断非空：
		 */
		if(pageNum==null || pageNum<=0){
			//设置默认当前页
			pageNum = 1;
		}
		if(pageSize == null){
			//设置默认每页显示的数据数
			pageSize = 1;
		}
		logger.info("当前页是："+pageNum+"显示条数是："+pageSize);

		//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
		PageHelper.startPage(pageNum,pageSize);
		//2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
		try {
			List<Document> documentList = documentService.findDocumentByUserId(userId);
			logger.info("分页数据："+documentList);
			logger.info("测试条数："+documentList.size());
			//3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
			PageInfo<Document> documentPageInfo = new PageInfo<Document>(documentList,pageSize);
			//4.使用model传参数回前端
			model.addAttribute("documentPageInfo",documentPageInfo);
			model.addAttribute("documentList",documentList);
		}finally {
			//清理 ThreadLocal 存储的分页参数,保证线程安全
			PageHelper.clearPage();
		}
		return "user/documentList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/4/4 20:21
	*Description  跳转到前台添加文档资料界面
	*/
	@GetMapping("/user-sys/document")
	public String toAddDocument(Model model,HttpSession session){
		List<Project> projectList = projectService.findByUserId(session.getAttribute("userId").toString());
		model.addAttribute("projectList",projectList);
		return "user/addDocument";
	}


	@Value("${uploadFile.resourceHandler}")
	private String resourceHandler;//请求 url 中的资源映射

	@Value("${uploadFile.filePath}")
	private String uploadFileLocation;//上传文件保存的本地目录

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/11 0:21
	*Description  前台添加文档资料，涉及文件上传到项目内指定路径下
	*/
	@PostMapping("/user-sys/document")
	public String addDocument(@RequestParam(value = "file", required = false) MultipartFile file,
	                          Document document,
	                          HttpServletRequest request,
	                          HttpSession session){
		if (file == null || file.isEmpty()) {
			return "上传文件为空...";
		}
		//basePath拼接完成后，形如：http://localhost:8081/TBMS
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		logger.info("basePath:"+basePath);
		String fileName = file.getOriginalFilename();
		logger.info("fileName:"+fileName);
		String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + fileName;
		logger.info("文件访问路径：" + fileServerPath);
		File saveFile = new File(uploadFileLocation, fileName);
		if(!saveFile.isDirectory()){
			//递归生成文件夹
			saveFile.mkdirs();
		}
		try {
			file.transferTo(saveFile);//文件保存
		} catch (IOException e) {
			e.printStackTrace();
		}
		String savePath = saveFile.getPath();
		logger.info("文件保存路径：" + savePath);
		//====================至此，文件上传结束，下面是插入文档资料进数据库
		//格式化上传时间为24小时制的当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String uploadTime = sdf.format(new Date());
		document.setDocumentName(fileName);
		document.setUploadTime(uploadTime);
		document.setAuthor(session.getAttribute("userName").toString());
		document.setSavePath(savePath);
		boolean result = documentService.saveDocument(document);
		if(result==true){
			logger.info("添加成功");
		}else{
			logger.info("添加失败");
		}
		return "redirect:/user-sys/documents/"+session.getAttribute("userId");
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/10 23:50
	*Description  删除文档资料
	*/
	@DeleteMapping("/admin-sys/document/{documentId}")
	@ResponseBody
	public boolean deleteDocument(@PathVariable("documentId") int documentId){
		logger.info("进入后台删除操作:"+documentId);
		boolean result = false;
		Document document = documentService.findByDocumentId(documentId);
		if(document!=null){
			String savePath = document.getSavePath();
			File file = new File(savePath);
			if(!file.exists()){
				logger.info("删除文件不存在");
				return result;
			}else{
				file.delete();
				logger.info("物理删除文件成功");
				result = documentService.deleteDocument(documentId);
				if(result){
					logger.info("删除成功");
				}else{
					logger.info("删除失败");
				}
			}
		}
		return result;
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/3/22 14:19
	*Description  后台搜索文档
	*/
	@GetMapping("/admin-sys/document-search/{keyword}")
	public String searchDocument(@PathVariable("keyword") String keyword,
	                         Model model,
	                         @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
	                         @RequestParam(defaultValue="10",value="pageSize")Integer pageSize){
		List<Document> documentList = null;
		//以下是分页显示
		if(pageNum==null || pageNum<=0){
			//设置默认当前页
			pageNum = 1;
		}
		if(pageSize == null){
			//设置默认每页显示的数据数
			pageSize = 1;
		}
		logger.info("当前页是："+pageNum+"显示条数是："+pageSize);

		//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
		PageHelper.startPage(pageNum,pageSize);
		//2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
		try {
			documentList = documentService.searchKeyword(keyword);
			logger.info("分页数据："+documentList);
			//3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
			PageInfo<Document> documentPageInfo = new PageInfo<Document>(documentList,pageSize);
			//4.使用model传参数回前端
			model.addAttribute("documentPageInfo",documentPageInfo);
			model.addAttribute("documentList",documentList);
			logger.info("打印搜索结果数："+documentList.size());
		}finally {
			//清理 ThreadLocal 存储的分页参数,保证线程安全
			PageHelper.clearPage();
		}
		return "admin/documentList";
	}

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/4/4 20:15
	*Description  前台搜索文档
	*/
	@GetMapping("/user-sys/document-search/{keyword}")
	public String searchDocumentByUserId(@PathVariable("keyword") String keyword,
	                             Model model,
	                             @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
	                             @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
		List<Document> documentList = null;
		//以下是分页显示
		if(pageNum==null || pageNum<=0){
			//设置默认当前页
			pageNum = 1;
		}
		if(pageSize == null){
			//设置默认每页显示的数据数
			pageSize = 1;
		}
		logger.info("当前页是："+pageNum+"显示条数是："+pageSize);

		//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
		PageHelper.startPage(pageNum,pageSize);
		//2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
		try {
			documentList = documentService.searchKeyword(keyword);
			logger.info("分页数据："+documentList);
			//3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
			PageInfo<Document> documentPageInfo = new PageInfo<Document>(documentList,pageSize);
			//4.使用model传参数回前端
			model.addAttribute("documentPageInfo",documentPageInfo);
			model.addAttribute("documentList",documentList);
			logger.info("打印搜索结果数："+documentList.size());
		}finally {
			//清理 ThreadLocal 存储的分页参数,保证线程安全
			PageHelper.clearPage();
		}
		return "user/documentList";
	}

	
}