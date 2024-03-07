package com.a602.actors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //리스너 넣을 때 사용
public class FtActorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtActorsApplication.class, args);
	}

}
