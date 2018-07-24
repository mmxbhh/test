package com.aisile.sellergoods.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSellerMapper;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.TbSellerExample;
import com.aisile.pojo.TbSellerExample.Criteria;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.sellergoods.service.SellerService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class SellerServiceImpl implements SellerService{

	@Autowired
	private TbSellerMapper sellerMapper;
	@Override
	public List<TbSeller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findPage(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(TbSeller seller) {
		// TODO Auto-generated method stub
		//初始化未审核通过
		seller.setStatus("0");
		//创建时间
		seller.setCreateTime(new Date());
		 sellerMapper.insert(seller);
	}

	
	@Override
	public TbSeller findOne(String id) {
		// TODO Auto-generated method stub
		return sellerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Result delete(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findSearch(TbSeller seller, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
	
		TbSellerExample example = new TbSellerExample();
		Criteria criteria = example.createCriteria();
		if(seller.getName()!=null && !seller.getName().equals("")){
			criteria.andNameLike("%"+seller.getName()+"%");
		}
		if(seller.getNickName()!=null && !seller.getNickName().equals("")){
			criteria.andNickNameLike("%"+seller.getNickName()+"%");
		}
		if(seller.getStatus()!=null && !seller.getStatus().equals("")){
			criteria.andStatusLike("%"+seller.getStatus()+"%");
		}
		Page<TbSeller> pageinfo = (Page<TbSeller>) sellerMapper.selectByExample(example);
		System.out.println(pageinfo.toString());
		return new PageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	
	@Override
	public void updateStatus(TbSeller seller, String status) {
		// TODO Auto-generated method stub
		seller.setStatus(status);
		sellerMapper.updateByPrimaryKey(seller);
	}

	@Override
	public Result update(TbSeller seller) {
		// TODO Auto-generated method stub
		return null;
	}

}
