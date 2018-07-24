package com.bw.service;

import java.util.List;

import com.bw.bean.Shop;
import com.bw.bean.User;

public interface UserService {

	User login(User u);

	List<Shop> getList();

	void toDel(String sid);

}
