package com.deepsank.SignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@MultipartConfig
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()){

            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            Part part = req.getPart("image");
            String fileName= part.getSubmittedFileName();

            //Store in DB:---
            // Path to your .db file
            String url = "jdbc:sqlite:D:/LearningProjects/SQLLite/Servlet_JSP_learnig.db";
            /*Explicitly Register the Driver
            While modern Java (JDBC 4.0+) loads drivers automatically,
            web environments sometimes fail to auto-discover drivers in
            WEB-INF/lib. Force the loading by adding this line before you call
            DriverManager.getConnection
            * */
            Class.forName("org.sqlite.JDBC");


            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    System.out.println("Connection to SQLite established!");
                }
                Thread.sleep(3000);
                String query = "Insert into Users(name,password,email,imageName) values (?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1,name);
                pstmt.setString(2,password);
                pstmt.setString(3,email);
                pstmt.setString(4,fileName);
                pstmt.executeUpdate();

                //upload at path
                InputStream is = part.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);
                String filePath = req.getRealPath("/")+"images"+ File.separator+fileName;

                FileOutputStream fos = new FileOutputStream(filePath);
                fos.write(data);
                fos.close();
                out.println("Done");
            } catch ( SQLException | InterruptedException e) {
                System.err.println(e.getMessage());
                out.println("Error");

            }

        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
