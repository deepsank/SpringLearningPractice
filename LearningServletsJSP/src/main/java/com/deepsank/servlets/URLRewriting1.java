package com.deepsank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class URLRewriting1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet S1</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet S1 at "+req.getContextPath()+"</h1>");
            String name = req.getParameter("name");
            out.println("<h1>Your Name is :-- "+name+"</h1>");
            out.println("<a href='urlrwservlet2?user="+name+"'>Go to URLRWServlet2</a>");   // URL Rewriting
            out.println("</body>");
            out.println("</html>");
        }
    }
}
