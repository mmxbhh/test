package com.bw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.bean.Shop;
import com.bw.bean.User;
import com.bw.dao.UserDao;
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public User login(User u) {
		// TODO Auto-generated method stub
		return dao.login(u);
	}

	@Override
	public List<Shop> getList() {
		// TODO Auto-generated method stub
		return dao.getList();
	}

	@Override
	public void toDel(String sid) {
		// TODO Auto-generated method stub
		dao.toDel(sid);
	}

}
