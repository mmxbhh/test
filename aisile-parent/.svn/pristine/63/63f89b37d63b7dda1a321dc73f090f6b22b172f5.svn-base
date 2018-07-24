package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.content.service.ContentCategoryService;
import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {
		@Reference
		private ContentCategoryService contentCategoryService;
		
		@RequestMapping("/findAll")
		public List<TbContentCategory> findAll(){
			return contentCategoryService.findAll();
		}
		@RequestMapping("/findPage")
		public PageResult findPage(int page,int rows){
			return contentCategoryService.findPage(page, rows);
					
		}
		
		@RequestMapping("/add")
		public Result add(@RequestBody TbContentCategory contentCategory){
			try {
				contentCategoryService.add(contentCategory);
				return new Result(true, "增加成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "增加失败");
			}
		}
		
		@RequestMapping("/update")
		public Result update(@RequestBody TbContentCategory contentCategory){
			try {
				contentCategoryService.update(contentCategory);
				return new Result(true, "修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "修改失败");
			}
		}	
		
		@RequestMapping("/findOne")
		public TbContentCategory findOne(Long id){
			return contentCategoryService.findOne(id);		
		}
		
		@RequestMapping("/delete")
		public Result delete(Long [] ids){
			try {
				contentCategoryService.delete(ids);
				return new Result(true, "删除成功"); 
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "删除失败");
			}
		}
		
		@RequestMapping("/search")
		public PageResult search(@RequestBody TbContentCategory contentCategory, int page, int rows  ){
			return contentCategoryService.findSearch(contentCategory, page, rows);		
		}
		
		
		
}
