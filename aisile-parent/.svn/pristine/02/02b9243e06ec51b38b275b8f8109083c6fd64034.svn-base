package com.aisile.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.content.service.ContentCategoryService;
import com.aisile.mapper.TbContentCategoryMapper;
import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.TbContentCategoryExample;
import com.aisile.pojo.entity.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<TbContentCategory> findAll() {
		// TODO Auto-generated method stub
		return contentCategoryMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(TbContentCategory contentCategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TbContentCategory contentCategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TbContentCategory findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageResult findSearch(TbContentCategory contentCategory, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);	//分页	
		TbContentCategoryExample example=new TbContentCategoryExample();  //条件
		com.aisile.pojo.TbContentCategoryExample.Criteria criteria = example.createCriteria(); //开始拼接条件 
		if(contentCategory.getName()!=null && !contentCategory.getName().equals("")){
			criteria.andNameLike("%"+contentCategory.getName()+"%");//标题名称
		}
		Page<TbContentCategory> page= (Page<TbContentCategory>)contentCategoryMapper.selectByExample(example);	
		return new PageResult(page.getTotal(), page.getResult());	
	}

}
