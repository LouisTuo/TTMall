package com.ttmall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttmall.mapper.TbItemMapper;
import com.ttmall.pojo.TbItem;
import com.ttmall.pojo.TbItemExample;
import com.ttmall.pojo.TbItemExample.Criteria;

/**
 * 测试
 * @author jaclon
 *
 */

public class TestPageHelper {
	@Test
	public void testPageHelper() throws Exception {   //这里不抛出移除会报错，为啥呢
		//1.获得mapper代理对象
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//2.设置分页
		PageHelper.startPage(1, 30);
		//3.执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//4.取分页后结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("total:"+total);
		int pages = pageInfo.getPages();
		System.out.println("pages:"+pages);
		int size = pageInfo.getSize();
		System.out.println("pagesize:"+size);
		
		TbItemExample example1 = new TbItemExample();
		Criteria criteria = example1.createCriteria();
		criteria.andIdEqualTo((long)536563);
		List<TbItem> list1 = itemMapper.selectByExample(example1);
		TbItem item = null;
		if (list1 != null && list1.size() > 0) {
			item = list1.get(0);
			System.out.println("item:"+item.getTitle());
		}
	}
}
