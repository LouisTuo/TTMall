package com.ttmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttmall.common.pojo.EasyUITreeNode;
import com.ttmall.mapper.TbItemCatMapper;
import com.ttmall.pojo.TbItemCat;
import com.ttmall.pojo.TbItemCatExample;
import com.ttmall.pojo.TbItemCatExample.Criteria;
import com.ttmall.service.ItemCatService;
/**
 * 商品分类
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		//根据parentId查询分类雷彪
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for(TbItemCat tbItemCatList:list){
			//创建节点对象
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCatList.getId());
			node.setText(tbItemCatList.getName());
			node.setState(tbItemCatList.getIsParent()?"closed":"open");
			//将节点添加到列表中
			resultList.add(node);
		}
		return resultList;
	}

}
