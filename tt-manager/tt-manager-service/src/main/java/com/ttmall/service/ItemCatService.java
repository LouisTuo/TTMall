package com.ttmall.service;

import java.util.List;

import com.ttmall.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	
	List<EasyUITreeNode> getItemCatList(long parentId);
}
