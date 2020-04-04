package com.icss.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.dao.UserDao;
import com.icss.vo.User1995;

public class UserService {
	public int LoginService(String username,String password) throws ClassNotFoundException, SQLException{
		//判断登录是否成功
		//status:0-正在审核，1-审核通过，登陆成功，2-审核未通过，3-用户名密码不正确
		int flag=3;
		UserDao ud=new UserDao();
		//user中已经有一条数据，3个字段
		User1995 user = ud.loginDao(username, password);
		//将user中的status值取出进行判断
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
			//如果在数据库中没查到数据，就是用户名或密码错误
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
