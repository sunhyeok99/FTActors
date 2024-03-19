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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class RabbitConfig {
	private static final String CHAT_QUEUE_NAME = "chat.queue";
	private static final String CHAT_EXCHANGE_NAME = "chat.exchange";
	private static final String ROUTING_KEY = "*.room.*";

	// Queue 등록
	@Bean
	public Queue queue(){
		return new Queue(CHAT_QUEUE_NAME, true);
	}

	// Exchange 등록
	@Bean
	public TopicExchange exchange(){
		return new TopicExchange(CHAT_EXCHANGE_NAME);
	}

	// Exchange와 Queue 바인딩
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder
			.bind(queue)
			.to(exchange)
			.with(ROUTING_KEY);
	}

	// RabbitMQ와의 메시지 통신을 담당하는 클래스
	@Bean
	public RabbitTemplate rabbitTemplate(){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(jsonMessageConverter());

		return rabbitTemplate;
	}

	// RabbitMQ와의 연결을 관리하는 클래스
	@Bean
	public ConnectionFactory connectionFactory(){
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setVirtualHost("/");	// ?? 이게 뭘까

		return factory;
	}

	// 메시지를 JSON 형식으로 직렬화하고 역직렬화하는데 사용되는 변환기
	// RabbitMQ 메시지를 JSON 형식으로 주고 받을 수 있음
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}
}
