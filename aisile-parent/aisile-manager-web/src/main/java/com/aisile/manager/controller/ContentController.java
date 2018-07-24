package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.content.service.ContentService;
import com.aisile.pojo.TbContent;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/content")
public class ContentController {
		@Reference
		private ContentService contentService;
		
		@RequestMapping("/findAll")
		public List<TbContent> findAll(){
			return contentService.findAll();
		}
		@RequestMapping("/findPage")
		public PageResult findPage(int page,int rows){
			return contentService.findPage(page, rows);
					
		}
		
		@RequestMapping("/add")
		public Result add(@RequestBody TbContent content){
			try {
				contentService.add(content);
				return new Result(true, "增加成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "增加失败");
			}
		}
		
		@RequestMapping("/update")
		public Result update(@RequestBody TbContent content){
			try {
				contentService.update(content);
				return new Result(true, "修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "修改失败");
			}
		}	
		
		@RequestMapping("/findOne")
		public TbContent findOne(Long id){
			return contentService.findOne(id);		
		}
		
		@RequestMapping("/delete")
		public Result delete(Long [] ids){
			try {
				contentService.delete(ids);
				return new Result(true, "删除成功"); 
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "删除失败");
			}
		}
		
		@RequestMapping("/search")
		public PageResult search(@RequestBody TbContent content, int page, int rows  ){
			return contentService.findSearch(content, page, rows);		
		}
		
		
		@RequestMapping("/updateStatus")
		public Result updateStatus(Long [] ids,String status){
			try {
				contentService.updateStatus(ids,status);
				return new Result(true, "提交成功"); 
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "提交失败");
			}
		}
		
}
