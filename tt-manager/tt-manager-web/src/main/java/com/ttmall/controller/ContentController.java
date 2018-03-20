package com.ttmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.pojo.TbContent;
import com.ttmall.service.ContentService;

/** 
 * 内容管理controller
 * @ClassName: ContentController 
 * @Description: TODO
 * @author: 庹
 * @date: 2017年11月2日 下午8:15:40  
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Resource(name="contentServiceImpl")
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public List getContentByCategoryId(@RequestParam Long categoryId){
		List<TbContent> contentByCategoryId = contentService.getContentByCategoryId(categoryId);
		return contentByCategoryId;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		
		TaotaoResult result = contentService.insertContent(content);
		return result;
		
	}
}
