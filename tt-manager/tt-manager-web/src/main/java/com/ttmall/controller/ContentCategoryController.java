package com.ttmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttmall.common.pojo.EasyUITreeNode;
import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.pojo.TbItemCat;
import com.ttmall.service.ContentCategoryService;

/**
 * 内容分类管理Controller
 * @author jaclon
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
		@Autowired
		private ContentCategoryService contentCategoryService;
		
		@RequestMapping("/list")
		@ResponseBody
		public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
			List<EasyUITreeNode> categoryList = contentCategoryService.getContentCategoryList(parentId);
			return categoryList;
		}
/*		@RequestMapping("/create")
		@ResponseBody
		public TaotaoResult createContentCategory(Long parentId,String name){
			TaotaoResult result = contentCategoryService.insertCategory(parentId, name);
			return result;
		}*/
		
	/*	@RequestMapping("/create")
		@ResponseBody
		public TaotaoResult createContentCategory(@RequestParam(value="parentId")Long parentId,@RequestParam(value="name")String name){
			System.out.println(parentId);
		//	TaotaoResult result = contentCategoryService.insertCategory(parentId, name);
			return null;
		}
		*/
		
		@RequestMapping("/create")
		@ResponseBody
		public TaotaoResult createContentCategory(@RequestBody TbItemCat itemCat){
			TaotaoResult result = contentCategoryService.insertCategory(itemCat.getParentId(), itemCat.getName());
			return result;
		}
		
		@RequestMapping("/update")
		@ResponseBody
		public TaotaoResult updateContentCategory(Long id,String name){
			TaotaoResult result = contentCategoryService.updateCategory(id, name);
			return result;
		}
}
