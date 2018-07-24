package com.aisile.sellergoods.service;

import java.util.List;
import com.aisile.pojo.TbGoods;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Goods;

public interface GoodsService {
	
	public List<TbGoods> findAllByParentId(Long parentId);
	//使用的是组合类
	public void add(Goods goods);
	
	public void update(Goods goods);
	
	public Goods findOne(Long id);
	
	public List<TbGoods> findOneBySelectIdAndParId(Long id);
	//343
	public void dele(Long [] ids);
	
	public List<TbGoods> findAll();
	
	public PageResult findPage(int page, int rows);
	//sdf
	public void delete(Long[] ids);
	
	public PageResult findSearch(TbGoods goods, int page, int rows);
	
	public void updateStatus(Long[] ids, String status);
	public void updateIsMarketable(Long[] ids, String isMarketable);
}
