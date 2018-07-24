package com.bw.dao;

import java.util.List;

import com.bw.bean.Shop;
import com.bw.bean.User;

public interface UserDao {

	User login(User u);

	List<Shop> getList();

	void toDel(String sid);

}
