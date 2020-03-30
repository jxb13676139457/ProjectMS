/**
 * FileName: DocumentMapper
 * Author:   小江
 * Date:     2020/2/5 18:08
 * History:
 */
package com.zhbit.xiaojiang.mapper;

import com.zhbit.xiaojiang.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "documentMapper")
public interface DocumentMapper {

	//查找所有Document对象
	List<Document> findAllDocument();

	//通过documentId查找Document对象
	Document findByDocumentId(int documentId);

	//保存Document对象
	int saveDocument(Document document);

	//删除Document对象
	int deleteDocument(int documentId);

	//按关键字搜索文档
	List<Document> findByKeyword(String keyword);

}