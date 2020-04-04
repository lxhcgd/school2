/**
  * <p>Title: TestFilter.java</p>
　 * <p>Description: </p>
　 * <p>Copyright: Copyright (c) 2020</p>
　 * <p>Company: </p>
　 * @author liuxin
　 * @date 2020年3月24日
　 * @version 1.0
 */
package com.icss.filter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * <p>Title: TestFilter</p>
　 * <p>Description: </p>
　 * @author liuxin
　 * @date 2020年3月24日
 */
public class TestFilter implements Filter {
	int start;
	int end;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	//实现过滤功能的方法
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		int hours = now.getHours();
		if(hours>start && hours<end){
			//将servletRequest强转成HttpServletRequest
			HttpServletRequest  request = (HttpServletRequest)arg0;
			HttpServletResponse response =(HttpServletResponse)arg1;
			//获得session对象
			HttpSession session = request.getSession();
			//判断登录情况
			String username = (String)session.getAttribute("xyz");	
			if(username==null){
					request.setAttribute("msg", "请登录");
					request.getRequestDispatcher("../index.jsp").forward(request, response);
			}
		}
		
		arg2.doFilter(arg0, arg1);
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//获得初始化参数
		start =Integer.parseInt(arg0.getInitParameter("start"));
		end = Integer.parseInt(arg0.getInitParameter("end"));
		
	}

	

}
