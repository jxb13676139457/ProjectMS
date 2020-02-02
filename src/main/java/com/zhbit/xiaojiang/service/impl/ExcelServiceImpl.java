/**
 * FileName: ExcelServiceImpl
 * Author:   小江
 * Date:     2020/1/26 22:29
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Role;
import com.zhbit.xiaojiang.entity.User;
import com.zhbit.xiaojiang.mapper.RoleMapper;
import com.zhbit.xiaojiang.mapper.UserMapper;
import com.zhbit.xiaojiang.service.ExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

	//Log4j日志打印
	private Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	private HSSFWorkbook workbook;
	private HSSFCell cell = null;  //Excel的列
	private HSSFRow row = null;  //Excel的行
	private HSSFCellStyle style, style1;

	@Override
	public String exportUsers(HttpServletResponse response) {
		//获取用户对象列表
		List<User> userList = userMapper.findAllUser();
		String[] tableHeader = {"用户ID", "用户名", "密码", "性别", "地址", "手机号", "角色名", "角色权限"};
		short cellNumber = (short) tableHeader.length;//表的列数
		workbook = new HSSFWorkbook(); //创建一个Excel
		style = workbook.createCellStyle(); //设置表头的类型
		style.setAlignment(HorizontalAlignment.CENTER);
		style1 = workbook.createCellStyle(); //设置数据类型
		style1.setAlignment(HorizontalAlignment.CENTER);
		HSSFFont font = workbook.createFont(); //设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet
		HSSFHeader header = sheet.getHeader();//设置sheet的头
		try {
			//根据是否取出数据，设置header信息
			if (userList.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("用户信息表");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				//表头
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell((short) k);//创建第0行第k列
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值
					sheet.setColumnWidth((short) k, (short) 8000);//设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeight((short) 350); //设置单元字体高度
					style1.setFont(font);//设置字体风格
					cell.setCellStyle(style1);
				}
				// 给Excel填充数据
				for (int i = 0; i < userList.size(); i++) {
					//获取User对象
					User user = (User) userList.get(i);
					row = sheet.createRow((short) (i + 1));//创建第i+1行
					row.setHeight((short) 400);//设置行高

					if (user.getUserId() != null) {
						cell = row.createCell((short) 0);//创建第i+1行第0列
						cell.setCellValue(user.getUserId());//设置第i+1行第0列的值
						cell.setCellStyle(style);//设置风格
					}
					if (user.getUserName() != null) {
						cell = row.createCell((short) 1); //创建第i+1行第1列
						cell.setCellValue(user.getUserName());//设置第i+1行第1列的值
						cell.setCellStyle(style); //设置风格
					}
					if (user.getPassword() != null) {
						cell = row.createCell((short) 2);
						cell.setCellValue(user.getPassword());
						cell.setCellStyle(style);
					}
					if (user.getSex() != null) {
						cell = row.createCell((short) 3);
						cell.setCellValue(user.getSex());
						cell.setCellStyle(style);
					}
					if (user.getAddress() != null) {
						cell = row.createCell((short) 4);
						cell.setCellValue(user.getAddress());
						cell.setCellStyle(style);
					}
					if (user.getPhone() != null) {
						cell = row.createCell((short) 5);
						cell.setCellValue(user.getPhone());
						cell.setCellStyle(style);
					}
					if (user.getRole().getRoleName() != null) {
						cell = row.createCell((short) 6);
						cell.setCellValue(user.getRole().getRoleName());
						cell.setCellStyle(style);
					}
					if (user.getRole().getRolePower() != null) {
						cell = row.createCell((short) 7);
						cell.setCellValue(user.getRole().getRolePower());
						cell.setCellStyle(style);
					}
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputSetting("用户信息表.xls",response);
		return null;
	}

	@Override
	public String exportRoles(HttpServletResponse response) {
		//获取角色对象列表
		List<Role> roleList = roleMapper.findAllRole();
		String[] tableHeader = {"角色ID", "角色类型", "角色名", "角色拥有权限"};
		short cellNumber = (short) tableHeader.length;//表的列数
		workbook = new HSSFWorkbook(); //创建一个Excel
		style = workbook.createCellStyle(); //设置表头的类型
		style.setAlignment(HorizontalAlignment.CENTER);
		style1 = workbook.createCellStyle(); //设置数据类型
		style1.setAlignment(HorizontalAlignment.CENTER);
		HSSFFont font = workbook.createFont(); //设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet
		HSSFHeader header = sheet.getHeader();//设置sheet的头
		try {
			//根据是否取出数据，设置header信息
			if (roleList.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("角色信息表");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				//表头
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell((short) k);//创建第0行第k列
					cell.setCellValue(tableHeader[k]);//设置第0行第k列的值
					sheet.setColumnWidth((short) k, (short) 8000);//设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeight((short) 350); //设置单元字体高度
					style1.setFont(font);//设置字体风格
					cell.setCellStyle(style1);
				}
				// 给Excel填充数据
				for (int i = 0; i < roleList.size(); i++) {
					//获取User对象
					Role role = (Role) roleList.get(i);
					row = sheet.createRow((short) (i + 1));//创建第i+1行
					row.setHeight((short) 400);//设置行高

					//填充角色ID
						cell = row.createCell((short) 0);//创建第i+1行第0列
						cell.setCellValue(role.getRoleId());//设置第i+1行第0列的值
						cell.setCellStyle(style);//设置风格

					if (role.getRoleType() != null) {
						cell = row.createCell((short) 1); //创建第i+1行第1列
						cell.setCellValue(role.getRoleType());//设置第i+1行第1列的值
						cell.setCellStyle(style); //设置风格
					}
					if (role.getRoleName() != null) {
						cell = row.createCell((short) 2);
						cell.setCellValue(role.getRoleName());
						cell.setCellStyle(style);
					}
					if (role.getRolePower() != null) {
						cell = row.createCell((short) 3);
						cell.setCellValue(role.getRolePower());
						cell.setCellStyle(style);
					}
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputSetting("角色信息表.xls",response);
		return null;
	}

	@Override
	public String downloadRoleExcel(HttpServletResponse response){
		String[] tableHeader = {"角色类型", "角色名", "角色拥有权限"};
		short cellNumber=(short)tableHeader.length;//表的列数
		workbook = new HSSFWorkbook(); //创建一个Excel
		style = workbook.createCellStyle(); //设置表头的类型
		style.setAlignment(HorizontalAlignment.CENTER);
		style1 = workbook.createCellStyle(); //设置数据类型
		style1.setAlignment(HorizontalAlignment.CENTER);
		HSSFFont font = workbook.createFont(); //设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet
		HSSFHeader header = sheet.getHeader();//设置sheet的头
		try {
			header.setCenter("角色信息表");
			row = sheet.createRow(0);
			row.setHeight((short)400);
			//表头
			for(int k = 0;k < cellNumber;k++){
				cell = row.createCell((short) k);//创建第0行第k列
				cell.setCellValue(tableHeader[k]);//设置第0行第k列的值
				sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度
				font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
				font.setFontHeight((short)350); //设置单元字体高度
				style1.setFont(font);//设置字体风格
				cell.setCellStyle(style1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputSetting("角色信息Excel模板.xls",response);
		return null;
	}

	@Override
	public String downloadUserExcel(HttpServletResponse response) {
		String[] tableHeader = {"用户ID", "用户名", "密码", "性别", "地址", "手机号"};
		short cellNumber=(short)tableHeader.length;//表的列数
		workbook = new HSSFWorkbook(); //创建一个Excel
		style = workbook.createCellStyle(); //设置表头的类型
		style.setAlignment(HorizontalAlignment.CENTER);
		style1 = workbook.createCellStyle(); //设置数据类型
		style1.setAlignment(HorizontalAlignment.CENTER);
		HSSFFont font = workbook.createFont(); //设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet
		HSSFHeader header = sheet.getHeader();//设置sheet的头
		try {
			header.setCenter("角色信息表");
			row = sheet.createRow(0);
			row.setHeight((short)400);
			//表头
			for(int k = 0;k < cellNumber;k++){
				cell = row.createCell((short) k);//创建第0行第k列
				cell.setCellValue(tableHeader[k]);//设置第0行第k列的值
				sheet.setColumnWidth((short)k,(short)8000);//设置列的宽度
				font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
				font.setFontHeight((short)350); //设置单元字体高度
				style1.setFont(font);//设置字体风格
				cell.setCellStyle(style1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputSetting("用户信息Excel模板.xls",response);
		return null;
	}

	@Override
	public Workbook getWorkbookByInputStream(InputStream iStream, String fileName) {
		Workbook workbook = null;
		try {
			if(null == fileName) {
				return null;
			}
			if(fileName.endsWith(".xls")) {
				workbook = new HSSFWorkbook(iStream);
			}else if(fileName.endsWith(".xlsx")){
				workbook = new XSSFWorkbook(iStream);
			}else {
				logger.info("仅支持excel文件");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (iStream != null){
				try {
					iStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}

	@Override
	public boolean isBlankRow(Row row) {
		if(row == null) {
			return true;
		}
		Iterator<Cell> iter = row.cellIterator();
		while(iter.hasNext()) {
			Cell cell = iter.next();
			if(cell == null) {
				continue;
			}
			String value = cell.getStringCellValue();
			if(value != null && !"".equals(value.toString())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Role> importRoleExcel(MultipartFile uploadFile, HttpServletRequest req, HttpServletResponse resp) {
		int rowNum = 0;//用来循环的行数
		int realRowCount = 0;//真正有数据的行数,总数据数
		Workbook workbook = null;
		//获得excel工作簿
		try {
			workbook = getWorkbookByInputStream(uploadFile.getInputStream(), uploadFile.getOriginalFilename());
			if(workbook==null){
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获得excel工作表
		Sheet sheet = workbook.getSheetAt(0);
		if(null == sheet) {
			sheet = workbook.createSheet();
		}
		//获取excel总记录数
		realRowCount = sheet.getPhysicalNumberOfRows();
		logger.info("excel总行数："+realRowCount);
		List<Role> roleList = new ArrayList<>();
		Role role = null;
		//循环读取excel的数据
		for(Row row:sheet) {
			if(realRowCount == rowNum) {
				break;
			}
			if(this.isBlankRow(row)) {//空行跳过
				logger.info("第"+row.getRowNum()+"行存在空数据");
				continue;
			}
			if(row.getRowNum() == -1) {
				continue;
			}else {
				if(row.getRowNum() == 0) {//第一行表头跳过
					continue;
				}
			}
			rowNum ++;
			role = new Role();
			//数据插入操作
			if (null != row.getCell(0) && "" != row.getCell(0).toString()) {
				role.setRoleType(row.getCell(0).getStringCellValue());
			}
			if(null != row.getCell(1) && "" != row.getCell(1).toString()) {
				role.setRoleName(row.getCell(1).getStringCellValue());
			}
			if(null != row.getCell(2) && "" != row.getCell(2).toString()) {
				role.setRolePower(row.getCell(2).getStringCellValue());
			}
			//避免插入重复角色信息
			if(roleMapper.findByRoleName(role.getRoleName())==null){
				logger.info(role.getRoleName());
				roleList.add(role);
			}else{
				logger.info("此角色对象已存在");
				continue;
			}
		}
		return roleList;
	}

	@Override
	public List<User> importUserExcel(MultipartFile uploadFile, HttpServletRequest req, HttpServletResponse resp) {
		int rowNum = 0;//用来循环的行数
		int realRowCount = 0;//真正有数据的行数,总数据数
		Workbook workbook = null;
		//获取excel工作簿
		try {
			workbook = getWorkbookByInputStream(uploadFile.getInputStream(),uploadFile.getOriginalFilename());
			if(workbook==null){
				return null;
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheetAt(0);
		//获取excel工作表
		if(null == sheet){
			workbook.createSheet();
		}
		//获取excel总数据数
		realRowCount = sheet.getPhysicalNumberOfRows();
		List<User> userList = new ArrayList<>();
		User user = null;
		for(Row row:sheet){
			if(realRowCount == rowNum) {
				break;
			}
			if(this.isBlankRow(row)) {//空行跳过
				logger.info("第"+row.getRowNum()+"行存在空数据");
				continue;
			}
			if(row.getRowNum() == -1) {
				continue;
			}else {
				if(row.getRowNum() == 0) {//第一行表头跳过
					continue;
				}
			}
			rowNum ++;
			user = new User();
			if(row.getCell(0)!=null && row.getCell(0).toString()!=""){
				user.setUserId(row.getCell(0).getStringCellValue());
			}
			if(row.getCell(1)!=null && row.getCell(1).toString()!=""){
				user.setUserName(row.getCell(1).getStringCellValue());
			}
			if(row.getCell(2)!=null){
				//设置类型为字符型，否则导入密码时报错
				row.getCell(2).setCellType(CellType.STRING);
				user.setPassword(row.getCell(2).getStringCellValue());
			}
			if(row.getCell(3)!=null && row.getCell(3).toString()!=""){
				user.setSex(row.getCell(3).getStringCellValue());
			}
			if(row.getCell(4)!=null && row.getCell(4).toString()!=""){
				user.setAddress(row.getCell(4).getStringCellValue());
			}
			if(row.getCell(5)!=null){
				//设置类型为字符型，否则导入手机号时报错
				row.getCell(5).setCellType(CellType.STRING);
				user.setPhone(row.getCell(5).getStringCellValue());
			}
			if(userMapper.findByUserId(user.getUserId())==null){
				logger.info(user.getUserId());
				userList.add(user);
			}else{
				logger.info("此用户对象已存在");
				continue;
			}
		}
		return userList;
	}

	//导出功能固定配置
	public void outputSetting(String fileName,HttpServletResponse response) {
		OutputStream out = null;//创建一个输出流对象
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes(),"ISO-8859-1"));//filename是下载的xls的名
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型
			response.setHeader("Pragma","No-cache");//设置头
			response.setHeader("Cache-Control","no-cache");//设置头
			response.setDateHeader("Expires", 0);//设置日期头
			workbook.write(out);
			out.flush();
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(out!=null){
					out.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}