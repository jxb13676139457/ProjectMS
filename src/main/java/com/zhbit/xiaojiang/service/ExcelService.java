/**
 * FileName: ExcelService
 * Author:   小江
 * Date:     2020/1/26 22:29
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.Role;
import com.zhbit.xiaojiang.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface ExcelService {

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/26 23:14
	*Description  导出所有用户信息
	*/
	String exportUsers(HttpServletResponse response);
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/26 23:14
	*Description  导出所有角色信息
	*/
	String exportRoles(HttpServletResponse response);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/28 23:47
	*Description  下载角色信息EXCEL模板
	*/
	String downloadRoleExcel(HttpServletResponse response);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/1/28 23:47
	*Description  下载用户信息EXCEL模板
	*/
	String downloadUserExcel(HttpServletResponse response);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/2 16:13
	*Description  批量导入角色信息
	*/
	List<Role> importRoleExcel(MultipartFile file, HttpServletRequest req, HttpServletResponse resp);
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/2 21:29
	*Description  批量导入用户信息
	*/
	List<User> importUserExcel(MultipartFile file, HttpServletRequest req, HttpServletResponse resp);

	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/2 16:21
	*Description  从输入流中获取excel工作表
	*/
	Workbook getWorkbookByInputStream(InputStream iStream, String fileName);
	
	/**
	*@Author 小江  [com.zhbit]
	*@Date 2020/2/2 16:34
	*Description  判断是否为空行数据
	*/
	boolean isBlankRow(Row row);
}