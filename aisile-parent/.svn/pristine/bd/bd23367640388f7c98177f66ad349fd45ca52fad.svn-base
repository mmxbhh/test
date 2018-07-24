package com.aisile.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbItemCatMapper;
import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbItemCatExample;
import com.aisile.pojo.TbItemCatExample.Criteria;
import com.aisile.sellergoods.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Service;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<TbItemCat> findAllByParentId(Long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		return itemCatMapper.selectByExample(example);
	}
	@Override
	public void add(TbItemCat itemCat) {
		// TODO Auto-generated method stub
		itemCatMapper.insert(itemCat);
		
	}
	@Override
	public void update(TbItemCat itemCat) {
		// TODO Auto-generated method stub
		itemCatMapper.updateByPrimaryKey(itemCat);
	}
	@Override
	public TbItemCat findOne(Long id) {
		// TODO Auto-generated method stub
		return itemCatMapper.selectByPrimaryKey(id);
	}
	@Override
	public void dele(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long long1 : ids) {
			itemCatMapper.deleteByPrimaryKey(long1);
		}
	}
	@Override
	public List<TbItemCat> findOneBySelectIdAndParId(Long id) {
		// TODO Auto-generated method stub
		
			return itemCatMapper.selectByExample1(id);
	}
	@Override
	public List<TbItemCat> findAll() {
		// TODO Auto-generated method stub
		return itemCatMapper.selectByExample(null);
	}
		
}
