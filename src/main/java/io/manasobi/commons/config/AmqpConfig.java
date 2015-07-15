package io.manasobi.commons.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableRabbit
public class AmqpConfig {

	/*@Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
		
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        
        container.setConnectionFactory(rabbitConnectionFactory());
        container.setQueueNames("manasobi");        
        
        return container;
    }*/
	/*@Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory());
        return factory;
    }*/

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
	
	/*@RabbitListener(queues = "manasobi")
    public void capitalize(License license) {
        System.out.println(license);
    }*/
}
