package com.aisile.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/login")
public class LonginController {
		
	@RequestMapping("/name")
	public Map name(){
		//spring的安全管理器,获取认证对象的名字
		Map<String,String> map = new HashMap<String,String>();
		String name = 	SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("loginName", name);
		return map;
	}
}
