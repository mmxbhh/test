package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.group.Specification;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.SpecificationService;
import com.aisile.sellergoods.service.TemplateService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/typeTemplate")
public class TemplateController {
		@Reference
		private TemplateService templateService;
		
		@RequestMapping("/findAll")
		public List<TbTypeTemplate> findAll(){
			return templateService.findAll();
		}
		
		@RequestMapping("/findPage")
		public PageResult  findPage(int page,int rows){			
			return templateService.findPage(page, rows);
		}
		
		@RequestMapping("/search")
		public PageResult search(@RequestBody TbTypeTemplate typeTemplate, int page, int rows  ){
			return templateService.findSearch(typeTemplate, page, rows);		
		}
		//添加
		@RequestMapping("/add")
		public Result add(@RequestBody TbTypeTemplate typeTemplate){
			try {
				templateService.add(typeTemplate);
				return new Result(true, "添加成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				return new Result(true, "添加失败");
			}
			
		}
		
		//修改
				@RequestMapping("/update")
				public Result update(@RequestBody TbTypeTemplate typeTemplate){
					try {
						templateService.update(typeTemplate);
						return new Result(true, "添加成功");
					} catch (Exception e) {
						// TODO: handle exception
						e.getMessage();
						return new Result(true, "添加失败");
					}
					
				}
		@RequestMapping("/findOne")
		public TbTypeTemplate  findOne(Long id){	
			return templateService.findOne(id);
		}
		
		@RequestMapping("/dele")
		public Result dele(Long [] ids){
			try {
				templateService.dele(ids);
				return new Result(true, "删除成功");
			} catch (Exception e) {
				// TODO: handle exception
				return new Result(false, "删除失败");
			}
		}
		
		@RequestMapping("/findOptionList")
		public List<Map> findOptionList(Long id){
			return templateService.findOptionList(id);
		}
		
}
