package com.bw.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{

	
	//调用方法之后执行
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	//调用方法之后返回结果之前执行
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	//调用方法之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//登录方法和页面不能拦截
		String path=request.getRequestURI();
		path=path.substring(path.lastIndexOf("/")+1);
	    if(path.equals("log.do") || path.equals("login.jsp")){
	    	return true;
	    }
		
		//如果用户已经登录则放行
		if(null!=request.getSession().getAttribute("user")){
			return true;
		}
		//如果不符合条件返回登录页面
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return false;
	}

}
