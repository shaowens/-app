package com.test.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.test.bean.Shiwu;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.test.util.rlDBUtil;;

public class ShowDao {
	public List<Shiwu> select(){
		Connection conn = rlDBUtil.getConn(); //连接数据库
	    List<Shiwu> list = new ArrayList<Shiwu>();
	    try {
	        String sql="select * from reliang";
	        Statement pstmt = (Statement) conn.createStatement();
	        ResultSet rs = (ResultSet) pstmt.executeQuery(sql);
	        while(rs.next()) {
	        	Shiwu Shiwu=new Shiwu();
	        	Shiwu.setClasses(rs.getString("classes"));
	        	Shiwu.setName(rs.getString("name"));
	        	Shiwu.setCalory(rs.getString("calory"));
	            list.add(Shiwu);
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();

	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}