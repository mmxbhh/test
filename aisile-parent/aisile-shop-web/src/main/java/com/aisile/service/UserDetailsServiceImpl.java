package com.aisile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aisile.pojo.TbSeller;
import com.aisile.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService{
/*
 * 	自定义认证类
 *  认证就是登陆
 *  授权就是给予权限
 *  角色就是授予角色
 *  验证就是验证权限时让它通过
 * 
 * **/
		private SellerService sellerService;
		
		public void setSellerService(SellerService sellerService) {
			this.sellerService = sellerService;
		}

	@Override
	//登陆谁，这会自动把值传进来
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		//相当于授予了一个角色,如果多个直接下面add添加多个角色就行了
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		TbSeller seller =  sellerService.findOne(username);
		if(seller!=null){
			if(seller.getStatus().equals("1")){
				return new User(username, seller.getPassword(), grantedAuths);
			}else{
				return null;
			}
			
		}else{
			return null;
		}
		
		//如果return 空就是登陆失败
	}
	
}
