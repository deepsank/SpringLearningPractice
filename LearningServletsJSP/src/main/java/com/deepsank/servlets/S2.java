package com.deepsank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class S2 extends HttpServlet {
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
            out.println("<h1>Servlet S2 at "+req.getContextPath()+"</h1>");

            String n1 = req.getParameter("n1");
            String n2 = req.getParameter("n2");
            int nn1 = Integer.parseInt(n1);
            int nn2 = Integer.parseInt(n2);
            int p = nn1*nn2;
            int sum = (int) req.getAttribute("sum");
            //add
            out.println("<h1>");
            out.println("Sum is: "+sum);
            out.println("Product is: "+p);
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
