package com.spring.aop;

import com.spring.aop.service.PaymentService;
import com.spring.aop.service.PaymentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        PaymentService paymentService = context.getBean("payment", PaymentService.class);

        // auth; print:PaymentStarted
        paymentService.makePayment(219);
    }
}
