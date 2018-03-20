package com.ttmall.service;

import com.ttmall.common.pojo.EasyUIDataGridResult;
import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.pojo.TbItem;


public interface ItemService {
	
  TbItem getItemById(Long itemId);
  
  public EasyUIDataGridResult getItemList(int page,int rows);
  
  public TaotaoResult createItem(TbItem item,String desc);
}
