package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.mapper.TbTypeTemplateMapper;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationExample;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.TbTypeTemplateExample;
import com.aisile.pojo.TbTypeTemplateExample.Criteria;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;
import com.aisile.sellergoods.service.TemplateService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper ;
	
	@Autowired
	private TbTypeTemplateMapper  typeTempateMapper;
	@Override
	public List<TbTypeTemplate> findAll() {
		// TODO Auto-generated method stub
		return typeTemplateMapper.selectByExample(null);
	}
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbTypeTemplate> page=   (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public PageResult findSearch(TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);		
		TbTypeTemplateExample example=new TbTypeTemplateExample();
		Criteria criteria = example.createCriteria();		
		if(typeTemplate!=null){
			if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
				criteria.andNameLike("%"+typeTemplate.getName()+"%");
			}
					
		}		
		Page<TbTypeTemplate> page= (Page<TbTypeTemplate>)typeTemplateMapper.selectByExample(example);	
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public void add(TbTypeTemplate typeTemplate) {
		// TODO Auto-generated method stub
		typeTemplateMapper.insert(typeTemplate);
	}
	
	@Override
	public TbTypeTemplate findOne(Long id) {
		// TODO Auto-generated method stub
		return typeTemplateMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void update(TbTypeTemplate typeTemplate) {
		// TODO Auto-generated method stub
		 typeTemplateMapper.updateByPrimaryKey(typeTemplate);
	}
	@Override
	public void dele(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long long1 : ids) {
			typeTemplateMapper.deleteByPrimaryKey(long1);
		}
	}
	@Override
	public List<Map> findOptionList(Long id) {
		// TODO Auto-generated method stub
		TbTypeTemplate typeTemplate = typeTempateMapper.selectByPrimaryKey(id);
		List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
		for (Map map : list) {
			map.get("id");
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));
			List<TbSpecificationOption> lists =  specificationOptionMapper.selectByExample(example);
			map.put("options", lists);	
			
		}
		return list;
	}
}
