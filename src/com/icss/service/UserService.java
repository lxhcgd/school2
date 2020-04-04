package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.dao.UserDao;
import com.icss.vo.User1995;

public class UserService {
	public int LoginService(String username,String password) throws ClassNotFoundException, SQLException{
		//�жϵ�¼�Ƿ�ɹ�
		//status:0-������ˣ�1-���ͨ������½�ɹ���2-���δͨ����3-�û������벻��ȷ
		int flag=3;
		UserDao ud=new UserDao();
		//user���Ѿ���һ�����ݣ�3���ֶ�
		User1995 user = ud.loginDao(username, password);
		//��user�е�statusֵȡ�������ж�
		if(user!= null){
			String status=user.getStatus();
			if("0".equals(status)){
				flag=0;
			}else if("1".equals(status)){
				flag=1;
			}else if("2".equals(status)){
				flag=2;
			}
		}else{
			//��������ݿ���û�鵽���ݣ������û������������
			flag=3;
		}
		return flag;
	}
	public ArrayList<User1995> selectAllUserService() throws ClassNotFoundException, SQLException{
		UserDao sd = new UserDao();
		ArrayList<User1995> al = sd.selectAllUserDao();
		return al;
	}
	public boolean selectNameService(String username) throws ClassNotFoundException, SQLException{
		UserDao ud = new UserDao();
		return ud.selectNameDao(username);
	}

}
