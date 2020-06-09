package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.*;

/**
 * Servlet implementation class LoginServlet
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
       LoginDao logindao = new LoginDao(); 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		String name=request.getParameter("name");
//		String password=request.getParameter("password");
//		String rname=logindao.searchUsername(name);
//		String rpassword=logindao.searchPassword(name);
//		if(name.equals(rname)&&password.equals(rpassword)) {
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//			System.out.println("IndexServlet跳转成功");
//		}
//		else {
//			request.getRequestDispatcher("denglu.jsp").forward(request,response);
//			System.out.println("IndexServlet跳转失败");
//			System.out.println(name+password);
//			System.out.println(rname+rpassword);
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String a=null;
		System.out.println(name);
		String rname=logindao.searchUsername(name);
		String rpassword=logindao.searchPassword(name);
		PrintWriter pw=response.getWriter();
		if(name.equals(rname)&&password.equals(rpassword)) {
			a="登录成功";
			pw.write(a+","+name);
			pw.close();
		}
		else {
			a="用户名或密码错误";
			pw.write(a+",");
			pw.close();
		}
		
		
	}
}
