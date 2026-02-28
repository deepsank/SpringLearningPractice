package com.springAI.SpringDocumentation;

import com.springAI.SpringDocumentation.configs.HintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.shell.command.annotation.CommandScan;


@ImportRuntimeHints(HintsRegistrar.class)
@CommandScan
@SpringBootApplication
public class SpringDocumentationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDocumentationApplication.class, args);
	}

}
