package com.deepsank.servlets;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FirstServlet implements Servlet {
    ServletConfig config;
    /* LifeCycle Method */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        System.out.println("Creating object:....");
    }
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servicing........");
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        out.println("<h1>This is my output from service method</>");
        out.println("<h1>Today's date and time is :"+ new Date().toString()+"</h1>");
    }
    @Override
    public void destroy() {
        System.out.println("Going to destroy servlet object");
    }
    /* Non-LifeCycle Method */

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }
    @Override
    public String getServletInfo() {
        return "The Servlet created by Deepak Kumar Upadhyay.";
    }

}
