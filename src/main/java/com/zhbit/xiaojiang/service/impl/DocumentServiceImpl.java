/**
 * FileName: DocumentServiceImpl
 * Author:   小江
 * Date:     2020/2/5 18:07
 * History:
 */
package com.zhbit.xiaojiang.service.impl;

import com.zhbit.xiaojiang.entity.Document;
import com.zhbit.xiaojiang.mapper.DocumentMapper;
import com.zhbit.xiaojiang.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentMapper documentMapper;

	//Log4j日志打印
	Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
	// 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
	public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";

	@Override
	public List<Document> findAllDocument() {
		return documentMapper.findAllDocument();
	}

	@Override
	public Document findByDocumentId(int documentId) {
		return documentMapper.findByDocumentId(documentId);
	}

	@Override
	public boolean saveDocument(Document document) {
		if(documentMapper.saveDocument(document)==1){
			logger.info("添加文档成功");
			return true;
		}else{
			logger.info("添加文档失败");
			return false;
		}
	}

	@Override
	public boolean deleteDocument(int documentId) {
		if(documentMapper.deleteDocument(documentId)>=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Document> searchKeyword(String keyword) {
		return documentMapper.findByKeyword(keyword);
	}

}