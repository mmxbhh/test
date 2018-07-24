package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;

public interface SpecificationService {
	//查询全部
	  public List<TbSpecification> findAll();
	 //分页v
	public PageResult findPage(int pageNum,int pageSize);
	//分页+模糊
	public PageResult findSearch(TbSpecification specification, int pageNum,int pageSize);
	//添加
	public void add(Specification specification);
	//查询id
	public Specification findOne(Long id);
	//修改
	public void update(Specification specification);
	//删除
	public void dele(Long[] ids);
	//模板里的下拉列表
	public List<Map> selectOptionList();

}
