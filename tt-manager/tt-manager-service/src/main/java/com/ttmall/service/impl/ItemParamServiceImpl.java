package com.ttmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.mapper.TbItemParamMapper;
import com.ttmall.pojo.TbItemParam;
import com.ttmall.pojo.TbItemParamExample;
import com.ttmall.pojo.TbItemParamExample.Criteria;
import com.ttmall.service.ItemParamService;
import com.ttmall.utils.EasyUIDataGridResult;
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	//查询规格参数表
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		//查询规格参数表
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// List<TbItemParam> list = tbItemParamMapper.selectByExample(example);
		//分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}

	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		
		//根据cid查询规格参数模板
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//执行查询
	//	List<TbItemParam> list = itemParamMapper.selectByExample(example);
			List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if(list!=null && list.size()>0){
			TbItemParam itemParam = list.get(0);
			return TaotaoResult.ok(itemParam);
		}
		return TaotaoResult.ok();
	}

	
	
}
