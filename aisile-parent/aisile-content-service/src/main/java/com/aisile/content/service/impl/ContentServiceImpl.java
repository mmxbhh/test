package com.aisile.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aisile.content.service.ContentService;
import com.aisile.mapper.TbContentMapper;
import com.aisile.pojo.TbContent;
import com.aisile.pojo.TbContentExample;
import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoodsExample.Criteria;
import com.aisile.pojo.entity.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private TbContentMapper contentMapper;
	//注入Spring Data Redis
		@Autowired
		private RedisTemplate redisTemplate;
	@Override
	public List<TbContent> findAll() {
		// TODO Auto-generated method stub
		return contentMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbContent> pageinfo = (Page<TbContent>) contentMapper.selectByExample(null);
		
		return new PageResult(pageinfo.getTotal(), pageinfo.getResult());
	}

	@Transactional
	public void add(TbContent content) {
		// TODO Auto-generated method stub
	
		if(	content.getStatus()==null){
			content.setStatus("0");
		}
		contentMapper.insert(content);
		//清楚缓存
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
	}

	@Override
	public void update(TbContent content) {
		// TODO Auto-generated method stub
		Long categoryId = contentMapper.selectByPrimaryKey(content.getId()).getCategoryId();
		//清除原来的分组缓存
		redisTemplate.boundHashOps("content").delete(categoryId);
		contentMapper.updateByPrimaryKey(content);
		//清除现在得到分组缓存
		if(categoryId.longValue()!=content.getCategoryId().longValue()){
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
		}
	}

	@Override
	public TbContent findOne(Long id) {
		// TODO Auto-generated method stub
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			//查询原来分组ID
			Long categoryId = contentMapper.selectByPrimaryKey(id).getCategoryId();
			//清除原来的分组缓存
			redisTemplate.boundHashOps("content").delete(categoryId);
			contentMapper.deleteByPrimaryKey(id);
		}
		
	}

	@Override
	public PageResult findSearch(TbContent content, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		TbContentExample example = new TbContentExample();
		 com.aisile.pojo.TbContentExample.Criteria criteria = example.createCriteria();
		 Page<TbContent> pageinfo =  (Page<TbContent>) contentMapper.selectByExample(example);
		return new PageResult(pageinfo.getTotal(), pageinfo.getResult());
	}

	@Override
	public List<TbContent> findByCategoryId(Long categoryId) {
		List<TbContent> contentList = (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);		
		if(contentList != null) {
			System.out.println("进来了!!!");
			return contentList;
		}else{
			//根据广告分类Id查询广告列表
			TbContentExample example = new TbContentExample();
			example.createCriteria().andCategoryIdEqualTo(categoryId);
			example.createCriteria().andStatusEqualTo("1");//因为查询的是默认开启状态 的列表
			example.setOrderByClause("sort_order");//排序 
			contentList = contentMapper.selectByExample(example);
			redisTemplate.boundHashOps("content").put(categoryId, contentList);//加入缓存   缓存同步
		}
		return contentList;
	}

	@Override
	public void updateStatus(Long[] ids, String status) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			TbContent tbContent = contentMapper.selectByPrimaryKey(id);
			tbContent.setStatus(status);
			contentMapper.updateByPrimaryKey(tbContent);
		}
	}

	

}
