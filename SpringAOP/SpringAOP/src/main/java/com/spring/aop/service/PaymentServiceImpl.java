package com.spring.aop.service;

public class PaymentServiceImpl implements PaymentService{

    @Override
    public void makePayment(int amount) {
        //mocked payment logic
        System.out.println("Amount debitted... "+ amount);
//
//
//
        System.out.println("Amount creditted... "+amount);
    }
}
