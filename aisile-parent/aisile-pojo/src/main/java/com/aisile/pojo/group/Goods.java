package com.aisile.pojo.group;

import java.io.Serializable;
import java.util.List;

import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoodsDesc;
import com.aisile.pojo.TbItem;

public class Goods implements Serializable{
		//1.spu
	private TbGoods tbgoods;
		//2.spu desc
	private TbGoodsDesc tbGoodsDesc;
		//3.sku列表
	private List<TbItem> itemsList;
	
	
	public TbGoods getTbgoods() {
		return tbgoods;
	}
	public void setTbgoods(TbGoods tbgoods) {
		this.tbgoods = tbgoods;
	}
	public TbGoodsDesc getTbGoodsDesc() {
		return tbGoodsDesc;
	}
	public void setTbGoodsDesc(TbGoodsDesc tbGoodsDesc) {
		this.tbGoodsDesc = tbGoodsDesc;
	}
	
	public List<TbItem> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<TbItem> itemsList) {
		this.itemsList = itemsList;
	}
	@Override
	public String toString() {
		return "Goods [tbgoods=" + tbgoods + ", tbGoodsDesc=" + tbGoodsDesc + ", itemsList=" + itemsList + "]";
	}
	
	
	
	
}
