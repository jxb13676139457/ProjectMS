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

	//通过documentId查找Document对象
	Document findByDocumentId(int documentId);

	//添加文档资料
	boolean saveDocument(Document document);

	//删除文档资料
	boolean deleteDocument(int documentId);

	//按关键字搜索文档
	List<Document> searchKeyword(String keyword);


}