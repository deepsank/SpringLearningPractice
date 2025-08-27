package com.dkupadhy.learningSpringBootApp.controller;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@RestController
//@Service
//@Repository
@Component
@ConditionalOnProperty(name= "payment.provider", havingValue = "razorpay")
public class RazorPaymentService  implements PaymentServiceInterface{

    public String pay(){
        String payment = "Razorpay payment";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
