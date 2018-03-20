package com.ttmall.service;

import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.utils.EasyUIDataGridResult;

public interface ItemParamService {
	public abstract EasyUIDataGridResult getItemParamList(int page,int rows);
	
	public abstract TaotaoResult getItemParamByCid(Long cid);
}
