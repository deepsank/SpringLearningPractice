package com.deepsank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HiddenFieldS1 extends HttpServlet {
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
            out.println("<form action='hiddedfieldS2'>"
                + "<input type='hidden' name='user_name' value='Deepak Kumar Upadhyay' />"
                + "<button type='submit'>Go to URLRWServlet2</button>"
                + "</form>"
            );
            out.println();
            out.println();   // URL Rewriting
            out.println("</body>");
            out.println("</html>");
        }
    }
}
