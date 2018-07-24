package com.aisile.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.entity.Result;
import com.aisile.sellergoods.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {
		@Reference
		private ItemCatService itemCatService;
		
		@RequestMapping("/findAllByParentId")
		public List<TbItemCat> findAllByParentId(Long parentId){
			return itemCatService.findAllByParentId(parentId);
		}
		
		@RequestMapping("/findAll")
		public List<TbItemCat> findAll(){
			return itemCatService.findAll();
		}
		
		@RequestMapping("/add")
		public Result add(@RequestBody TbItemCat itemCat){
			try {
				itemCatService.add(itemCat);
				return new Result(true, "保存成功");
			} catch (Exception e) {
				// TODO: handle exception
				return new Result(false, "保存失败");
			}
		}
		
		@RequestMapping("/update")
		public Result update(@RequestBody TbItemCat itemCat){
			try {
				itemCatService.update(itemCat);
				return new Result(true, "修改成功");
			} catch (Exception e) {
				// TODO: handle exception
				return new Result(false, "修改失败");
			}
		}
		@RequestMapping("/delete")
		public Result dele(Long [] ids){
			try {
				itemCatService.dele(ids);
				return new Result(true, "修改成功");
			} catch (Exception e) {
				// TODO: handle exception
				return new Result(false, "修改失败");
			}
		}
		@RequestMapping("findOne")
		public TbItemCat findOne(Long id){
			return itemCatService.findOne(id);
		}
		@RequestMapping("/findOneBySelectIdAndParId")
		public List<TbItemCat> findOneBySelectIdAndParId(Long id){
			return itemCatService.findOneBySelectIdAndParId(id);
		}
		
}
