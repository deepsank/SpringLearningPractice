package com.deepsank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Welcome to Register Servlet</h2>");
        String name = req.getParameter("user_name");
        String password = req.getParameter("user_password");
        String email = req.getParameter("user_email");
        String gender = req.getParameter("user_gender");
        String course = req.getParameter("user_course");
        String condition = req.getParameter("condition");

        if("checked".equals(condition)){
            out.println("<h2> Name : "+ name+ " </h2>");
            out.println("<h2> Password : "+ password+ " </h2>");
            out.println("<h2> Email : "+ email+ " </h2>");
            out.println("<h2> Gender : "+ gender+ " </h2>");
            out.println("<h2> Course : "+ course+ " </h2>");
            //Processing such as saving into DB using JDBC
            // processing done
            /* 2 methods of Request Dispatcher ---
            1. forward()
            2. include()
             */
            RequestDispatcher rd = req.getRequestDispatcher("success");
            rd.forward(req,resp);
        }
        else {
            out.println("<h2>You have not accepted terms and conditions.</h2>");
            //get the object of RequestDispatcher
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            //include
            rd.include(req,resp);

        }
    }
}
