package com.aisile.sellergoods.service;

import java.util.List;

import com.aisile.pojo.TbItemCat;

public interface ItemCatService {
		//根据父亲ID获取列表
	public List<TbItemCat> findAllByParentId(Long parentId);

	public void add(TbItemCat itemCat);
	
	public void update(TbItemCat itemCat);
	
	public TbItemCat findOne(Long id);
	public List<TbItemCat> findOneBySelectIdAndParId(Long id);
	public void dele(Long [] ids);

	public List<TbItemCat> findAll();
}
