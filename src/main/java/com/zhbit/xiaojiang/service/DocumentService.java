/**
 * FileName: DocumentService
 * Author:   小江
 * Date:     2020/2/5 18:06
 * History:
 */
package com.zhbit.xiaojiang.service;

import com.zhbit.xiaojiang.entity.Document;

import java.util.List;

public interface DocumentService {

	//查找所有Document对象
	List<Document> findAllDocument();

	//添加文档资料
	boolean saveDocument(Document document);

	//删除文档资料
	boolean deleteDocument(int documentId);


}