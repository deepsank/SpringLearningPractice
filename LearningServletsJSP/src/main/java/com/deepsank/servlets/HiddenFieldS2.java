package com.deepsank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HiddenFieldS2 extends HttpServlet {
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
            out.println("<title>Servlet S2</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet S1 at "+req.getContextPath()+"</h1>");
            String user = req.getParameter("user_name");
            out.println("<h1 style='color:blue;'>Welcome to servlet 2</h1>");
            out.println("<h1 style='color:red;'>Welcome back "+user+" </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
