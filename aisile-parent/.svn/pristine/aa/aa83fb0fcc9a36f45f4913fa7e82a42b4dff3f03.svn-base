package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;

public interface TemplateService {

	List<TbTypeTemplate> findAll();

	PageResult findPage(int page, int rows);

	PageResult findSearch(TbTypeTemplate typeTemplate, int page, int rows);

	public void add(TbTypeTemplate typeTemplate);

	public TbTypeTemplate findOne(Long id);

	public void update(TbTypeTemplate typeTemplate);

	public void dele(Long[] ids);
	//规格选项使用
	public List<Map> findOptionList(Long id); 
}
