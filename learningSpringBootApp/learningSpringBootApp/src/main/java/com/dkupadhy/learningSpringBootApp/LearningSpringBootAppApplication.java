package com.dkupadhy.learningSpringBootApp;

import com.dkupadhy.learningSpringBootApp.controller.PaymentServiceInterface;
import com.dkupadhy.learningSpringBootApp.controller.RazorPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Set;

@SpringBootApplication
public class LearningSpringBootAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("नमस्ते, विश्व!");
		SpringApplication.run(LearningSpringBootAppApplication.class, args);

//		HashMap<Integer,String> hmap = new HashMap<>();
//		String sanket = hmap.put(1, "Sanket");
//		Set<Integer> integers = hmap.keySet();

	}

//	private RazorPaymentService paymentService = new RazorPaymentService();    //Normal java code

//	@Autowired 			//Field Injection-- Used to define that LearningSpringBootAppApplication class is dependent on
						// RazorPaymentService, so that Spring Container injects the dependency automatically,
						// does not allow final for the dependency declarations
//	private final RazorPaymentService paymentService;

	//  Constructor Injection ---- If we don't do any of these Field/Constructor Injection, then we get error as
	// Container doesn't get to know that this main class of ours is dependent on the dependency
//	public LearningSpringBootAppApplication(RazorPaymentService paymentService){
//		this.paymentService = paymentService;
//	}

	private PaymentServiceInterface paymentService;

	public LearningSpringBootAppApplication(PaymentServiceInterface paymentService){
		this.paymentService = paymentService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Payment done: "+ paymentService.pay());
	}
}
