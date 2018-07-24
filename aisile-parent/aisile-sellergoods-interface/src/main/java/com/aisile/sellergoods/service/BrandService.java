package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.entity.PageResult;

public interface BrandService {
	//查询全部
     public List<TbBrand> findAll();
     //分页
     public PageResult findPage(int pageNum,int pageSize);
     //增加
     public void add(TbBrand brand);
     //修改
     public void update(TbBrand brand);
     //回显
     public TbBrand findOne(Long id);
     //删除
     public void delete(Long [] ids);
     //分页+模糊
 	public PageResult findSearch(TbBrand brand, int pageNum,int pageSize);
 	//模板里的下拉列表
 	public List<Map> selectOptionList();
 	
}
