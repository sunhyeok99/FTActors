package com.a602.actors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/config.yml")

public class FtActorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtActorsApplication.class, args);
	}

}
