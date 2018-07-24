package com.aisile.content.service;

import java.util.List;

import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.entity.PageResult;

public interface ContentCategoryService {
	//查询全部
    public List<TbContentCategory> findAll();
    //分页
    public PageResult findPage(int pageNum,int pageSize);
    //增加
    public void add(TbContentCategory contentCategory);
    //修改
    public void update(TbContentCategory contentCategory);
    //回显
    public TbContentCategory findOne(Long id);
    //删除
    public void delete(Long [] ids);
    //分页+模糊
	public PageResult findSearch(TbContentCategory contentCategory, int pageNum,int pageSize);

	
}
