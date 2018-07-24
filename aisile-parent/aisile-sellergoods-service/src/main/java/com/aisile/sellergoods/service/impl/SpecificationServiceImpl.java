package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSpecificationMapper;
import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.mapper.TbTypeTemplateMapper;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationExample;
import com.aisile.pojo.TbSpecificationExample.Criteria;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;
import com.aisile.sellergoods.service.SpecificationService;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class SpecificationServiceImpl implements SpecificationService {
	@Autowired
	private TbSpecificationMapper SpecificationMapper;
	@Autowired
	private TbSpecificationOptionMapper  specificationOptionMapper;
	
	
	@Override
	public List<TbSpecification> findAll() {
		// TODO Auto-generated method stub
		return SpecificationMapper.selectByExample(null);
	}
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) SpecificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public PageResult findSearch(TbSpecification specification, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();		
		if(specification!=null){
			if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
					
		}		
		Page<TbSpecification> page= (Page<TbSpecification>)SpecificationMapper.selectByExample(example);	
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public void add(Specification specification) {
		// TODO Auto-generated method stub
		TbSpecification tbSpe = specification.getSpecification();
		//添加规格
		SpecificationMapper.insert(tbSpe);
		//System.out.println(tbSpe.getId());
		//循环添加规格明细
		for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
			option.setSpecId(tbSpe.getId());
			specificationOptionMapper.insert(option);
		}
		
	}
	
	@Override
	public Specification findOne(Long id) {
		// TODO Auto-generated method stub
		//查询规格
		TbSpecification tbSpec = SpecificationMapper.selectByPrimaryKey(id);
		//根据规格实体的id 查询规格选项列表
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria  = example.createCriteria();
		criteria.andSpecIdEqualTo(tbSpec.getId());//根据规格ID查询
		List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
		//构建组合实体类返回结果
		Specification spec=new Specification();
		spec.setSpecification(tbSpec);
		spec.setSpecificationOptionList(optionList);		
		
		return spec;
	}
	
	@Override
	public void update(Specification specification) {
		// TODO Auto-generated method stub
		TbSpecification tbspe = specification.getSpecification();
		SpecificationMapper.updateByPrimaryKey(tbspe);
		//删除原来规格相对应的
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(tbspe.getId());
		specificationOptionMapper.deleteByExample(example);
		for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
			option.setSpecId(tbspe.getId());
			specificationOptionMapper.insert(option);
		}
	}
	
	@Override
	public void dele(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long long1 : ids) {
			//删除规格
			SpecificationMapper.deleteByPrimaryKey(long1);
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(long1);//指定规格ID为条件
			specificationOptionMapper.deleteByExample(example);//删除
		}
	}
	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return SpecificationMapper.selectOptionList();
	}
	
}
