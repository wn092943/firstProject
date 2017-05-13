package com.netease;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class NoodlesServlet extends HttpServlet {
	 private static final Logger logger = Logger.getLogger(NoodlesServlet.class);  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        PrintWriter writer = response.getWriter();
//        String vegetable = request.getParameter("vegetable");
//        if (vegetable == null) {
//            vegetable = "Tomato";
//        }
//
//        writer.println("<html><body>");
//        writer.println("<h1> Noodles with " + vegetable + "</h1>"); 
//        writer.println("</body></html>");
//        // ��־��¼�����м�����ʲô
//        logger.info("�����߲�:" + vegetable);
        
   
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取本次登录名与密码
    	String user = request.getParameter("user");
        String passward = request.getParameter("password");
        // 获取session，并记录密码
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("passward", passward);;
        // 创建cookie并记录用户名，设置cookie持续时间为30分钟
    	Cookie cookie = new Cookie("user", user);
    	cookie.setMaxAge(30 * 60);
    	response.addCookie(cookie);
        //
        Cookie[] cookies = request.getCookies();
        // 
        if (cookies != null) {
        	// 使session失效
        	httpSession.invalidate();
        	for (Cookie curCookie : cookies) {
        		if (curCookie.getName().equals("user")) {
        			// 获取cookie记录的用户名
        			String recordUser = curCookie.getValue();
        			PrintWriter writer = response.getWriter();
        			writer.println("<html>");
        			writer.println("<body>");
        			writer.println("<p>本次登录名称" + user + "</p>");
        			// 本次与上一次不一致
        			if (!recordUser.equals(user)) {
        				writer.println("<p>与上次登陆名不一致!</p>");
        				writer.println("<p>上次登录名称" + recordUser + "</p>");
        			}
        			writer.println("</body>");
    				writer.println("</html>");
        			writer.close();
        		}
        	}
        }
    }
}
