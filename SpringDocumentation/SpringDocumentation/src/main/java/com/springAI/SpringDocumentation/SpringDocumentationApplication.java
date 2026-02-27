package com.springAI.SpringDocumentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class SpringDocumentationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDocumentationApplication.class, args);
	}

}
