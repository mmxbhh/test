package com.aisile.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aisile.mapper.TbBrandMapper;
import com.aisile.mapper.TbGoodsDescMapper;
import com.aisile.mapper.TbGoodsMapper;
import com.aisile.mapper.TbItemCatMapper;
import com.aisile.mapper.TbItemMapper;
import com.aisile.mapper.TbSellerMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoodsDesc;
import com.aisile.pojo.TbGoodsExample;
import com.aisile.pojo.TbGoodsExample.Criteria;
import com.aisile.pojo.TbItem;
import com.aisile.pojo.TbItemExample;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Goods;
import com.aisile.sellergoods.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;
	
	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbBrandMapper brandMapper;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;	
	
	@Autowired
	private TbSellerMapper sellerMapper;	
	
	
	@Override
	
	public List<TbGoods> findAllByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void add(Goods goods) {
		/**
		 * 因为使用的是组合类，所以这儿传的是工具类里的tbgoods对象
		 *   1、保存spu
		 * */
		goods.getTbgoods().setAuditStatus("0");//未审核状态
		goodsMapper.insert(goods.getTbgoods());
		//可以获得goods插入的id了 进行第二步:desc表
		goods.getTbGoodsDesc().setGoodsId(goods.getTbgoods().getId());
		goodsDescMapper.insert(goods.getTbGoodsDesc());
		//第三步  sku
		
		List<TbItem> list1 = goods.getItemsList();
		if("1".equals(goods.getTbgoods().getIsEnableSpec())){
		
		for (TbItem tbItem : list1) {
			//需要哪些属性
			//商品标题，根据规格拼接
			String title = goods.getTbgoods().getGoodsName();
			Map<String, Object> specMap = JSON.parseObject(tbItem.getSpec());
			for (String maps : specMap.keySet()) {
				title+=""+specMap.get(maps);
			}
			tbItem.setTitle(title);//标题
			setItemValue(goods,tbItem);
			itemMapper.insert(tbItem);
		}//list循环的括号
		}else{
			//没有使用规格,需要使用默认规格
			TbItem tbItem = new TbItem();
			//商品标题 根据规格拼接
			String title = goods.getTbgoods().getGoodsName();
			tbItem.setTitle(title);//标题
			tbItem.setPrice(goods.getTbgoods().getPrice());//价格
			tbItem.setStatus("1");//状态
			tbItem.setIsDefault("1");//是否默认
			tbItem.setNum(99999);//库存数量
			tbItem.setSpec("{}");//表中表的默认规范
			setItemValue(goods,tbItem);
			itemMapper.insert(tbItem);
		}
	}
		//公共方法封装
		private void setItemValue(Goods goods,TbItem tbItem){
			tbItem.setGoodsId(goods.getTbgoods().getId());//spuId
			tbItem.setSellerId(goods.getTbgoods().getSellerId());//商家ID
			tbItem.setCategoryid(goods.getTbgoods().getCategory3Id());//商品分类编写
			tbItem.setCreateTime(new Date());//添加时间
			tbItem.setUpdateTime(new Date());//修改时间
			//分类名称
			TbBrand brand = brandMapper.selectByPrimaryKey(goods.getTbgoods().getBrandId());
			tbItem.setBrand(brand.getName());
			//商家名称
			TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getTbgoods().getSellerId());//获得商家对象
			tbItem.setSeller(seller.getNickName());//商家店铺ID

			//加载图片地址(spu的第一个图片)
			List<Map> imgs = JSON.parseArray(goods.getTbGoodsDesc().getItemImages(),Map.class);
			if(imgs!=null){
				if(imgs.size()>0){
					tbItem.setImage((String) imgs.get(0).get("url"));//图片路径
				}
			}
		}
	
	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		goods.getTbgoods().setAuditStatus("0");//设置未申请状态,如果是经过修改的商品，需要重新设置状态
		goodsMapper.updateByPrimaryKey(goods.getTbgoods());//保存商品表
		goodsDescMapper.updateByPrimaryKey(goods.getTbGoodsDesc());
		//删除所有SKU的列表数据
		TbItemExample example = new TbItemExample();
		example.createCriteria().andGoodsIdEqualTo(goods.getTbgoods().getId());
		itemMapper.deleteByExample(example);
		//添加新的SKU列表
		saveItemList(goods);
		
	}


	private void saveItemList(Goods goods) {
		List<TbItem> list1 = goods.getItemsList();
		if("1".equals(goods.getTbgoods().getIsEnableSpec())){
			
			for (TbItem tbItem : list1) {
				//需要哪些属性
				//商品标题，根据规格拼接
				String title = goods.getTbgoods().getGoodsName();
				Map<String, Object> specMap = JSON.parseObject(tbItem.getSpec());
				for (String maps : specMap.keySet()) {
					title+=""+specMap.get(maps);
				}
				tbItem.setTitle(title);//标题
				setItemValue(goods,tbItem);
				itemMapper.insert(tbItem);
			}//list循环的括号
			}else{
				//没有使用规格,需要使用默认规格
				TbItem tbItem = new TbItem();
				//商品标题 根据规格拼接
				String title = goods.getTbgoods().getGoodsName();
				tbItem.setTitle(title);//标题
				tbItem.setPrice(goods.getTbgoods().getPrice());//价格
				tbItem.setStatus("1");//状态
				tbItem.setIsDefault("1");//是否默认
				tbItem.setNum(99999);//库存数量
				tbItem.setSpec("{}");//表中表的默认规范
				setItemValue(goods,tbItem);
				itemMapper.insert(tbItem);
			}
	}

	@Override
	public List<TbGoods> findOneBySelectIdAndParId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//真正删除
	public void dele(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			goodsMapper.deleteByPrimaryKey(id);
		}
	}
	@Override
	public Goods findOne(Long id) {
		// TODO Auto-generated method stub
		
		Goods goods = new Goods();
		//商品的基本信息 用于回显
		TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
		goods.setTbgoods(tbGoods);
		TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		goods.setTbGoodsDesc(tbGoodsDesc);
		TbItemExample example = new TbItemExample();
		example.createCriteria().andGoodsIdEqualTo(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		goods.setItemsList(list);
		return goods;
	}

	@Override
	public List<TbGoods> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findPage(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			TbGoods goods = goodsMapper.selectByPrimaryKey(id);
			goods.setIsDelete("1");
			goodsMapper.updateByPrimaryKey(goods);
		}
		
	}

	@Override
	public PageResult findSearch(TbGoods goods, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageSize, pageNum);
		TbGoodsExample example = new TbGoodsExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsDeleteIsNull();//查询没有删除的
		if(goods.getSellerId()!=null && goods.getSellerId().length()>0 ){
			criteria.andSellerIdEqualTo(goods.getSellerId());
		}
		if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
			criteria.andAuditStatusEqualTo(goods.getAuditStatus());
		}
		if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
			criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
		}
		Page<TbGoods> page =  (Page<TbGoods>) goodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void updateStatus(Long[] ids, String status) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			TbGoods goods1 = goodsMapper.selectByPrimaryKey(id);
			goods1.setAuditStatus(status);
			goodsMapper.updateByPrimaryKey(goods1);
		}
	}

	@Override	
	public void updateIsMarketable(Long[] ids, String isMarketable) {
		for(Long id:ids){
			TbGoods goods1 = goodsMapper.selectByPrimaryKey(id);
			goods1.setIsMarketable(isMarketable);;
			goodsMapper.updateByPrimaryKey(goods1);
		}
	}

}
