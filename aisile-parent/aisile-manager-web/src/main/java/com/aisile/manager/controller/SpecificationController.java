package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.group.Specification;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.SpecificationService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
		@Reference
		private SpecificationService specificationService;
		
		@RequestMapping("/findAll")
		public List<TbSpecification> findAll(){
			return specificationService.findAll();
		}
		
		@RequestMapping("/findPage")
		public PageResult  findPage(int page,int rows){			
			return specificationService.findPage(page, rows);
		}
		@RequestMapping("/findOne")
		public Specification  findOne(Long id){	
			return specificationService.findOne(id);
		}
		
		@RequestMapping("/search")
		public PageResult search(@RequestBody TbSpecification specification, int page, int rows  ){
			return specificationService.findSearch(specification, page, rows);		
		}
		@RequestMapping("/add")
		public Result add(@RequestBody Specification specification){
			try {
				specificationService.add(specification);
				return new Result(true, "添加成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				return new Result(true, "添加失败");
			}
			
		}
		//修改
		@RequestMapping("/update")
		public Result update(@RequestBody Specification specification){
			try {
				specificationService.update(specification);
				return new Result(true, "添加成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				return new Result(true, "添加失败");
			}
			
		}
		//删除
		@RequestMapping("/dele")
		public Result dele(Long [] ids){
			try {
				specificationService.dele(ids);		
				return new Result(true, "添加成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				return new Result(true, "添加失败");
			}
			
		}
		@RequestMapping("/selectOptionList")
		public List<Map> selectOptionList(){
			return specificationService.selectOptionList();
		}
}
