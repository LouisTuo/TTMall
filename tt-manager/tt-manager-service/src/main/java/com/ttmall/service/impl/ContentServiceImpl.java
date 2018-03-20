package com.ttmall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.mapper.TbContentMapper;
import com.ttmall.pojo.TbContent;
import com.ttmall.pojo.TbContentExample;
import com.ttmall.pojo.TbContentExample.Criteria;
import com.ttmall.service.ContentService;

/** 
 * 内容管理service
 * @ClassName: ContentServiceImpl 
 * @Description: TODO
 * @author: 庹
 * @date: 2017年11月2日 下午8:17:37  
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public List<TbContent> getContentByCategoryId(Long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
		
	}

	@Override
	public TaotaoResult insertContent(TbContent content) {
		content.setUpdated(new Date());
		content.setCreated(new Date());
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}
	
}
