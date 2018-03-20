package com.ttmall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttmall.common.pojo.EasyUITreeNode;
import com.ttmall.common.pojo.TaotaoResult;
import com.ttmall.mapper.TbContentCategoryMapper;
import com.ttmall.pojo.TbContentCategory;
import com.ttmall.pojo.TbContentCategoryExample;
import com.ttmall.pojo.TbContentCategoryExample.Criteria;
import com.ttmall.service.ContentCategoryService;

/** 
 * 内容分类管理service
 * @ClassName: ContentCategoryServiceImpl 
 * @Description: TODO
 * @author: 庹
 * @date: 2017年10月24日 下午8:36:16  
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
		//查询条件
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//转行成TreeNode列表
		List<EasyUITreeNode> result = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建TreeNode的节点
			 EasyUITreeNode node = new EasyUITreeNode();
			 node.setId(tbContentCategory.getId());
			 node.setText(tbContentCategory.getName());
			 node.setState(tbContentCategory.getIsParent()?"closed":"open");
			 //条件到列表
			 result.add(node);
		}
		return result;
	}

	@Override
	public TaotaoResult insertCategory(Long parentId, String name) {
		//创建一个POJO对象
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setStatus(1);  // "1"正常   "2"删除
		contentCategory.setIsParent(false);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入数据
		contentCategoryMapper.insert(contentCategory);
		//取返回的主键
		/*Long id = contentCategory.getId();
		System.out.println("插入内容分类的主键id"+id);*/
		// 通过上面的方法无法取出id，所以通过查询
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		Long id = list.get(0).getId();
		//判断父节点的isParent属性
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			//更新父节点
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		return  TaotaoResult.ok(id);
	}
	
	public TaotaoResult updateContentCategory(){
		
		
		return null;
	}

	@Override
	public TaotaoResult updateCategory(Long id, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setId(id);
		contentCategory.setName(name);
	//	contentCategoryMapper.updateByPrimaryKey(contentCategory);
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return null;
	}

}
