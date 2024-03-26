package com.a602.actors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@PropertySource("classpath:/config.yml")
@EnableJpaAuditing //리스너 넣을 때 사용
@EnableMongoRepositories	// MongoDB 사용
@EnableMongoAuditing
public class FtActorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtActorsApplication.class, args);
	}

}
