package com.dkupadhy.learningSpringBootApp.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name= "payment.provider", havingValue = "stripe")
public class StripePaymentService implements PaymentServiceInterface{
    public String pay(){
        String payment = "Stripe payment";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
