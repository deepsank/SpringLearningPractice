package com.dkupadhy.learningSpringBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Set;

@SpringBootApplication
public class LearningSpringBootAppApplication {

	public static void main(String[] args) {
		System.out.println("नमस्ते, विश्व!");
		SpringApplication.run(LearningSpringBootAppApplication.class, args);

		HashMap<Integer,String> hmap = new HashMap<>();
		String sanket = hmap.put(1, "Sanket");
		Set<Integer> integers = hmap.keySet();

	}

}
