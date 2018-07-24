package com.aisile.sellergoods.service;

import java.util.List;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;

public interface SellerService {
	public List<TbSeller> findAll();
	public PageResult findPage(int page,int rows);
	public void add(TbSeller seller);
	public Result update(TbSeller seller);
	public TbSeller findOne(String id);
	public Result delete(Long [] ids);
	public PageResult findSearch( TbSeller seller, int page, int rows  );
	public void updateStatus(TbSeller seller, String status);
}
