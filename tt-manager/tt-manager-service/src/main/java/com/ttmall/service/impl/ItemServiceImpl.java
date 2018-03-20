package com.ttmall.service.impl;


import java.util.Date;

/**
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttmall.common.pojo.EasyUIDataGridResult;
import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.mapper.TbItemDescMapper;
import com.ttmall.mapper.TbItemMapper;
import com.ttmall.pojo.TbItem;
import com.ttmall.pojo.TbItemDesc;
import com.ttmall.pojo.TbItemExample;
import com.ttmall.pojo.TbItemExample.Criteria;
import com.ttmall.service.ItemService;
import com.ttmall.utils.IDUtils;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public TbItem getItemById(Long itemId) {
		
		/*TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;*/
		
	    //添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
	    TbItem item ;
		if (list != null && list.size() > 0) {
				item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	// 添加商品
	public TaotaoResult createItem(TbItem item, String desc) {
		// TODO Auto-generated method stub
		//生成商品ID
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除'
		item.setStatus((byte)1);
		//创建时间和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//插入商品表
		itemMapper.insert(item);
		//商品描述
		TbItemDesc itemdesc = new TbItemDesc();
		itemdesc.setItemId(itemId);
		itemdesc.setItemDesc(desc);
		itemdesc.setCreated(date);
		itemdesc.setUpdated(date);
		//插入商品描述数据
		tbItemDescMapper.insert(itemdesc);
		
		return TaotaoResult.ok();
	}


}
