package io.manasobi.commons.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        
        connectionFactory.setAddresses("192.168.0.9");
        connectionFactory.setVirtualHost("manasobi-host");
        connectionFactory.setUsername("manasobi");
        connectionFactory.setPassword("manasobi");
        
        return connectionFactory;
    }
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		
		rabbitTemplate.setQueue("manasobi");
		//rabbitTemplate.setRoutingKey("manasobi.key");
		//rabbitTemplate.setExchange("manasobi.key");
		rabbitTemplate.setRoutingKey("key.manasobi");
		rabbitTemplate.setExchange("manasobi");
		
		rabbitTemplate.setConnectionFactory(rabbitConnectionFactory());
			
		return rabbitTemplate;
	}
	
}
