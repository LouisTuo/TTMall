package com.ttmall.service;

import java.util.List;

import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.pojo.TbContent;

/** 
 * @ClassName: ContentService 
 * @Description: TODO
 * @author: 庹
 * @date: 2017年11月2日 下午8:16:50  
 */
public interface ContentService {

	List<TbContent> getContentByCategoryId(Long categoryId);
	
	TaotaoResult insertContent(TbContent content);
}
