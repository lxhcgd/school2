package com.icss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.UserService;

/**
 * Servlet implementation class ValidateNameService
 */
@WebServlet("/ValidateNameService")
public class ValidateNameService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateNameService() {
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
		String username = request.getParameter("username");
		//��������
		UserService us = new UserService();
		try {
			boolean b = us.selectNameService(username);
			String str= "";
			if(b==true){
				str = "��ǰ�û�������";
			}else{
				str = "�û�������";
			}
			//�������˸��ͻ��˴������ݵķ�ʽ���ַ���/XML/json
			//�ַ�����ʽ
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			//������ʽ��Ҫˢ��ҳ���
			//request.setAttribute("msg", str);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
