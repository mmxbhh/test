package com.aisile.content.service;

import java.util.List;

import com.aisile.pojo.TbContent;
import com.aisile.pojo.entity.PageResult;

public interface ContentService {
	//查询全部
    public List<TbContent> findAll();
    //分页
    public PageResult findPage(int pageNum,int pageSize);
    //增加
    public void add(TbContent content);
    //修改
    public void update(TbContent content);
    //回显
    public TbContent findOne(Long id);
    //删除
    public void delete(Long [] ids);
    //分页+模糊
	public PageResult findSearch(TbContent content, int pageNum,int pageSize);
	//根据广告分类Id查询广告列表
	public List<TbContent> findByCategoryId(Long categoryId);
	//开启关闭
	public void updateStatus(Long[] ids, String status);

	
}
