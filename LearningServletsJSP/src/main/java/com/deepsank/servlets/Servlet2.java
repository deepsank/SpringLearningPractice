package com.deepsank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet {
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
            //getting all the cookies
            Cookie[] cookies = req.getCookies();
            boolean f = false;
            String name="";
            if(cookies==null){
               out.println("<h1>You are a new user, got to home page and submit your name</h1>");
            return;
            }
            else{
                for (Cookie c : cookies){
                    String tname = c.getName();
                    if(tname.equals("user_name")){
                        f = true;
                        name= c.getValue();
                        break;
                    }
                }
            }
            if(f){
                out.println("<h1>Hello, Name :- "+name+"</h1>");
                out.println("<h1>Thanks a Lot!!!</h1>");
            }
            else {
                out.println("<h1>You are a new user, got to home page and submit your name</h1>");
            }
            out.println("<h1>Servlet S2 at "+req.getContextPath()+"</h1>");
//            String name = req.getParameter("name");
//            out.println("<h1>Name is :-- "+name+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
