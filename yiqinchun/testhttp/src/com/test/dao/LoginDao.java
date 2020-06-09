package com.test.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import com.test.util.*;
public class LoginDao {
	public static String searchUsername(String name) {
		String sql="select * from user where ";
		if(name!=null) {
			sql=sql+"name like+'%"+name+"%'";
		}
		Connection conn=DBUtil.getConn();
		Statement state=null; 
		ResultSet rs=null;
		String name1=null;
		try {
		state=conn.createStatement();
		rs=state.executeQuery(sql);
		while(rs.next())
		{
			name1=rs.getString("name");
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,state, conn);
		}
		return name1;
		
	}
	public static String searchPassword(String name) {
		String sql="select * from user where ";
		if(name!=null) {
			sql=sql+"name like '%"+name+"%'";
		}
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		String password1=null;
		try {
		state=conn.createStatement();
		rs=state.executeQuery(sql);
		while(rs.next()) {
			password1=rs.getString("password");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs,state,conn);
		}
		return password1;
	}

}
