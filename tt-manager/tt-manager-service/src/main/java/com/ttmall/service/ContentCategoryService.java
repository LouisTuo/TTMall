package com.ttmall.service;

import java.util.List;

import com.ttmall.common.pojo.EasyUITreeNode;
import com.ttmall.common.pojo.TaotaoResult;

/** 
 * 查询内容分类service 
 * @ClassName: ContentCategoryService 
 * @Description: TODO
 * @author: 庹
 * @date: 2017年10月24日 下午8:28:01  
 */
public interface ContentCategoryService {
	
	public List<EasyUITreeNode> getContentCategoryList(Long parentId);
	
	TaotaoResult insertCategory(Long parentId,String name);
	
	TaotaoResult updateCategory(Long id,String name);
}
