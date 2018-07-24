 package com.aisile.manager.controller;

import java.util.List;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbGoods;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.group.Goods;
import com.aisile.sellergoods.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
@RestController
@RequestMapping("/goods")
public class GoodsController {
	
	@Reference
	private GoodsService goodsService;
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){
		return goodsService.findAll();
	}
	@RequestMapping("/findPage")
	public PageResult findPage(int page,int rows){
		return goodsService.findPage(page, rows);
				
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody Goods goods){
		try {
			//因为是登录进来的  表里面有用户名  所以要根据安全框架认证获取id
			String seller_id = SecurityContextHolder.getContext().getAuthentication().getName();
			goods.getTbgoods().setSellerId(seller_id);
			goodsService.add(goods);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			return new Result(false, "增加失败");
		}
	}
	
	@RequestMapping("/update")
	public Result update(@RequestBody Goods goods){
		//出于安全考虑，在商户后台执行的商品修改，必须要校验提交的商品属于该商户
		try {
			Goods goods2 = goodsService.findOne(goods.getTbgoods().getId());
			String seller_id = SecurityContextHolder.getContext().getAuthentication().getName();
			//如果传递过来的商家ID不是当前登录的商家Id
			if(!goods2.getTbgoods().getSellerId().equals(seller_id) || !goods.getTbgoods().getSellerId().equals(seller_id)){
				return new Result(false, "操作非法");
			}
			goodsService.update(goods);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		return goodsService.findOne(id);		
	}
	
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGoods goods,int page, int rows  ){
		
		return goodsService.findSearch(goods, page, rows);		
	}
	@RequestMapping("/updateStatus")
	public Result updateStatus(Long[] ids,String status){
		try {
			goodsService.updateStatus(ids,status);
			return new Result(true, "修改成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
	
}
