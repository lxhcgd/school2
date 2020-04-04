package com.icss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接受请求
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		//处理请求
		UserService us= new UserService();
		try {
			int flag= us.LoginService(username, password);
			if(flag==0){
				//正在审核，跳转到index.jsp
				request.setAttribute("msg", "正在审核，请等待");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if(flag==1){
				//************************此时登陆成功********************************************
				//************************Cookie简化登录,记住密码，在一段时间内不需要再输入用户名和密码*********************************
				String time=request.getParameter("time");
				int days=0;
				//days:0-每次都登录，1-记住用户名和密码1分钟，3-记住用户名和密码三分钟
				//如果有数据
				if(time!=null){
					//将接收到的数据强制类型转换成int型
					days = Integer.parseInt(time);
					//创建cookie对象
					Cookie usernameCookie = new Cookie("username", username);
					Cookie passwordCookie = new Cookie("password", password);
					//设置cookie有效时间，存储在本地磁盘中，此时还未生效
					usernameCookie.setMaxAge(days*60);
					passwordCookie.setMaxAge(days*60);
					//使cookie生效，存储在客户端
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				}
				//************************Cookie简化登录*********************************
				
				//************************session登录访问控制，每次访问新的页面时判断是否有用户是登陆成功的状态，如购买时******
				//1.获取session
				HttpSession session = request.getSession();
				//2.设置最大非活动时间
				session.setMaxInactiveInterval(30*60);
				//3.将用户名存到session中
				session.setAttribute("username",username);
				//************************session登录访问控制*******************************
				
				//*************************ServletContext 统计登录人次***********************************
				//1.从上下文对象中获取存入的值
				ServletContext sc = this.getServletContext();
				Object countStr = sc.getAttribute("counts");
				int count = 0;
				//2.判断是第一次登录还是第一次以后登录
				if(countStr==null){
					//如果是第一次登录，将count值变为1
					count = 1;
				}else{
					//如果不是第一次登录，将变量加一
					//先将object类型转换成字符串类型，在将字符串类型喜欢换成int类型
					count = Integer.parseInt(countStr.toString());
					count++;
				}
				//3.将处理好的count变量存入到上下文中
				sc.setAttribute("counts", count);
				
				
				
				//*************************ServletContext 统计登录人次***********************************
				//审核通过，跳转到successlogin.jsp
				request.setAttribute("a",username);
				request.getRequestDispatcher("loginsuccess.jsp").forward(request, response);
			}else if(flag==2){
				//审核未通过，跳转到index.jsp
				request.setAttribute("msg", "审核未通过，登录失败");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if(flag==3){
				//用户名或密码错误，跳转到index.jsp
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
