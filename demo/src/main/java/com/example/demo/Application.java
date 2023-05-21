package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Knight knight = context.getBean(Knight.class);
		knight.embarkOnQuest();
		((AnnotationConfigApplicationContext) context).close();
	}
}
