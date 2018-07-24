package com.bw.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{

	
	//���÷���֮��ִ��
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	//���÷���֮�󷵻ؽ��֮ǰִ��
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	//���÷���֮ǰִ��
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//��¼������ҳ�治������
		String path=request.getRequestURI();
		path=path.substring(path.lastIndexOf("/")+1);
	    if(path.equals("log.do") || path.equals("login.jsp")){
	    	return true;
	    }
		
		//����û��Ѿ���¼�����
		if(null!=request.getSession().getAttribute("user")){
			return true;
		}
		//����������������ص�¼ҳ��
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return false;
	}

}
