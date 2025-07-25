package com.springMvcSearch.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // Your logic here
        System.out.println("Inside preHandle() method of MyInterceptor class......");
        String name = request.getParameter("querybox");
        if(name.startsWith("#")){
            response.setContentType("text/html");
            response.getWriter().println("<h1>Invalid query, should not start with 'd', its not allowed!!!</h1>");
            return false;
        }
        return true; // continue execution chain
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Post-processing

        System.out.println("Inside postHandle() method of MyInterceptor class......");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        // Cleanup
        System.out.println("Inside afterCompletion() method of MyInterceptor class......");

    }
}
