package com.a602.actors.global.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableRabbit
@Slf4j
public class RabbitConfig {
	// private static final String CHAT_QUEUE_NAME = "chat.queue";
	// private static final String CHAT_EXCHANGE_NAME = "chat.exchange";
	private static final String ROUTING_KEY = "*.room.0";

	// @Value("${rabbit.queue-name}")
	// private static String CHAT_QUEUE_NAME;
	// @Value("${rabbit.exchange-name}")
	// private static String CHAT_EXCHANGE_NAME;
	// @Value("${rabbit.routing-key}")
	// private static String ROUTING_KEY;
	// @Value("${rabbit.user_name}")
	// private static String USER_NAME;
	// @Value("${rabbit.password}")
	// private static String PASSWORD;


	// Queue 등록
	@Bean
	public Queue queue(@Value("${rabbit.queue-name}") String CHAT_QUEUE_NAME){
		log.info("queue 등록 !!");
		// return new Queue(CHAT_QUEUE_NAME, true);
		return new Queue(CHAT_QUEUE_NAME);
	}

	// Exchange 등록
	@Bean
	public TopicExchange exchange(@Value("${rabbit.exchange-name}") String CHAT_EXCHANGE_NAME){
		log.info("exchange 등록 !!");
		return new TopicExchange(CHAT_EXCHANGE_NAME);
	}

	// Exchange와 Queue 바인딩
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange){
	// public Binding binding(){
		log.info("binding 등록 !!");
		return BindingBuilder
			.bind(queue)
			.to(exchange)
			.with(ROUTING_KEY);
	}

	// RabbitMQ와의 메시지 통신을 담당하는 클래스
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		log.info("rabbitTemplate 등록 !!");
		return rabbitTemplate;
	}

	// RabbitMQ와의 연결을 관리하는 클래스
	@Bean
	public ConnectionFactory connectionFactory(@Value("${rabbit.user_name}") String USER_NAME, @Value("${rabbit.password}") String PASSWORD){
		log.info("connectionFactory 등록 !!");
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		// factory.setPort(58153);
		factory.setVirtualHost("/");	// ?? 이게 뭘까
		factory.setUsername(USER_NAME);
		factory.setPassword(PASSWORD);

		log.info("host : {}", factory.getHost());
		log.info("port : {}", factory.getPort());
		log.info("virtual host : {}", factory.getVirtualHost());
		log.info("username : {}", factory.getUsername());

		return factory;
	}

	// 메시지를 JSON 형식으로 직렬화하고 역직렬화하는데 사용되는 변환기
	// RabbitMQ 메시지를 JSON 형식으로 주고 받을 수 있음
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}
}
