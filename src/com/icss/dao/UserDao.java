package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.util.DBUtil;
import com.icss.vo.User1995;

public class UserDao {
	public User1995 loginDao(String username,String password) throws SQLException, ClassNotFoundException{
		PreparedStatement pStatement = DBUtil.getConnection().prepareStatement("select * from user1995 where username=?and password=?");
		pStatement.setString(1, username);
		pStatement.setString(2, password);
		//结果集
		ResultSet rs= pStatement.executeQuery();
		User1995 user = new User1995();
		while(rs.next()){
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			user.setStatus(rs.getString(3));
		}
		rs.close();
		pStatement.close();
		DBUtil.getConnection().close();
		return user;
	}
	public ArrayList<User1995> selectAllUserDao() throws ClassNotFoundException, SQLException{
		PreparedStatement ps = DBUtil.getConnection().prepareStatement
				("select * from user1995");
		ResultSet rs = ps.executeQuery();
		ArrayList<User1995> al = new ArrayList<User1995>();
		while(rs.next()){
			User1995 user = new User1995();
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			user.setStatus(rs.getString(3));
			al.add(user);
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return al;
	}
	//验证用户名是否存在
	public boolean selectNameDao(String username) throws ClassNotFoundException, SQLException{
		PreparedStatement ps = DBUtil.getConnection().prepareStatement
				("select * from user1995 where username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		boolean  flag = false;
		while(rs.next()){
			flag=true;
		}
		rs.close();
		ps.close();
		DBUtil.getConnection().close();
		return flag;
	}

}
