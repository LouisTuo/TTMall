package com.ttmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttmall.common.pojo.EasyUIDataGridResult;
import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.pojo.TbItem;
import com.ttmall.service.ItemService;

/**
 * 商品查询controller
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	
	/*注入*/
	@Autowired     
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){   // 这里public和private都可以的
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){   //这里使用包装数据类型，因为这里变量可以为空
		EasyUIDataGridResult list = itemService.getItemList(page, rows);
		return list;
	}
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc){
		return itemService.createItem(item, desc);
	}
	
	
	
}
