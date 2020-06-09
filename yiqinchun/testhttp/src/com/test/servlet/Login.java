package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	String uname=req.getParameter("uname");
//	String upass=req.getParameter("upass");
//	
//	String a=null;
//	if("admin".equals(uname) && "123".equals(upass)){
//	a="succes";
//	System.out.println(a);
//	}else{
//	a="fail";
//	System.out.println(a);
//	}
//	PrintWriter pw=resp.getWriter();
//	pw.write(a);
//	pw.close();
//	}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String uname=req.getParameter("uname");
String upass=req.getParameter("upass");
System.out.println(uname);
System.out.println(upass);
String a=null;
if("admin".equals(uname) && "123".equals(upass)){
a="success";
System.out.println(a);
}else{
a="fail";
System.out.println(a);
}
PrintWriter pw=resp.getWriter();
pw.write(a);
pw.close();
}
}