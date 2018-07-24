package com.bw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.bw.bean.Shop;
import com.bw.bean.User;
import com.bw.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("login")
	@ResponseBody
	public int login(HttpServletRequest request,User u){
		int i=0;
		User user=service.login(u);
		if(null!=user){
			request.getSession().setAttribute("user", user);
			i=1;
		}
		return i;
	}
	
	@RequestMapping("list")
	public String list(HttpServletRequest request){
		List<Shop> list=service.getList();
		request.setAttribute("list", list);
		return "list";
	}
	
	@RequestMapping("toDel")
	public String toDel(String sid){
		service.toDel(sid);
		return "redirect:list.do";
	}
}
