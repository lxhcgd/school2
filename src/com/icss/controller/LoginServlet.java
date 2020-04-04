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
		//��������
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		//��������
		UserService us= new UserService();
		try {
			int flag= us.LoginService(username, password);
			if(flag==0){
				//������ˣ���ת��index.jsp
				request.setAttribute("msg", "������ˣ���ȴ�");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if(flag==1){
				//************************��ʱ��½�ɹ�********************************************
				//************************Cookie�򻯵�¼,��ס���룬��һ��ʱ���ڲ���Ҫ�������û���������*********************************
				String time=request.getParameter("time");
				int days=0;
				//days:0-ÿ�ζ���¼��1-��ס�û���������1���ӣ�3-��ס�û���������������
				//���������
				if(time!=null){
					//�����յ�������ǿ������ת����int��
					days = Integer.parseInt(time);
					//����cookie����
					Cookie usernameCookie = new Cookie("username", username);
					Cookie passwordCookie = new Cookie("password", password);
					//����cookie��Чʱ�䣬�洢�ڱ��ش����У���ʱ��δ��Ч
					usernameCookie.setMaxAge(days*60);
					passwordCookie.setMaxAge(days*60);
					//ʹcookie��Ч���洢�ڿͻ���
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				}
				//************************Cookie�򻯵�¼*********************************
				
				//************************session��¼���ʿ��ƣ�ÿ�η����µ�ҳ��ʱ�ж��Ƿ����û��ǵ�½�ɹ���״̬���繺��ʱ******
				//1.��ȡsession
				HttpSession session = request.getSession();
				//2.�������ǻʱ��
				session.setMaxInactiveInterval(30*60);
				//3.���û����浽session��
				session.setAttribute("username",username);
				//************************session��¼���ʿ���*******************************
				
				//*************************ServletContext ͳ�Ƶ�¼�˴�***********************************
				//1.�������Ķ����л�ȡ�����ֵ
				ServletContext sc = this.getServletContext();
				Object countStr = sc.getAttribute("counts");
				int count = 0;
				//2.�ж��ǵ�һ�ε�¼���ǵ�һ���Ժ��¼
				if(countStr==null){
					//����ǵ�һ�ε�¼����countֵ��Ϊ1
					count = 1;
				}else{
					//������ǵ�һ�ε�¼����������һ
					//�Ƚ�object����ת�����ַ������ͣ��ڽ��ַ�������ϲ������int����
					count = Integer.parseInt(countStr.toString());
					count++;
				}
				//3.������õ�count�������뵽��������
				sc.setAttribute("counts", count);
				
				
				
				//*************************ServletContext ͳ�Ƶ�¼�˴�***********************************
				//���ͨ������ת��successlogin.jsp
				request.setAttribute("a",username);
				request.getRequestDispatcher("loginsuccess.jsp").forward(request, response);
			}else if(flag==2){
				//���δͨ������ת��index.jsp
				request.setAttribute("msg", "���δͨ������¼ʧ��");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if(flag==3){
				//�û��������������ת��index.jsp
				request.setAttribute("msg", "�û������������");
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
