package com.core.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

import com.alibaba.fastjson.JSONObject;
import com.domain.enums.RabbitMQ;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

/**
 * @EnableRabbit 打开mq开关
 * @ConditionalOnClass(AmqpTemplate.class) 有这个AmqpTemplate 这个类，执行RabbitConfig
 * @ConditionalOnProperty(name = "spring.rabbitmq.host") 在配置文件中有 spring.rabbitmq.host mq的host 执行这个类
 * @ConditionalOnClass && @ConditionalOnProperty 两个条件必须都要满足
 * 也就是说，有mq的jar包加上mq的配置信息，才会加载mq的配置类，创建@bean
 */

@Configuration
@EnableRabbit
@ConditionalOnClass(AmqpTemplate.class)
@ConditionalOnProperty(name = "spring.rabbitmq.host")
public class RabbitConfig {	
	
	/**
	 * 分装RabbitTemplate，用AmqpTemplate 也是可以的
	 * @param connectionFactory  springboot 默认配置了
	 * @return
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange(RabbitMQ.QUE_EXCHANGE);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());// 数据转换为json存入消息队列
		return rabbitTemplate;
	}
	
	/**
	 * 封装监听mq 消费
	 * 主要功能1.把json数据转对象，2.记录消息错误
	 * @param configurer
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory) {

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setErrorHandler(errorHandler());// 消费错误处理
		return factory;
	}
	
	/**
	 * 直连交换机模式 que -> que
	 * 默认是持久化
	 */
	@Bean
	DirectExchange defaultDirectExchange() {

		return new DirectExchange(RabbitMQ.QUE_EXCHANGE,true,false);
	}

	/**
	 * 主题交换机模式 （通配符模式）
	 */
	@Bean
	TopicExchange defaultTopicExchange() {

		return new TopicExchange(RabbitMQ.TOPIC_EXCHANGE,true,false);
	}
	
	/**
	 * 动态创建que
	 * @param connectionFactory
	 * @throws IOException 
	 * @throws TimeoutException 
	 */
	@Bean
    public Runnable autoCreate(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
		Channel channel = connectionFactory.createConnection().createChannel(false);
		RabbitMQ[] ques = RabbitMQ.values();
		for(RabbitMQ que: ques){			
			try {
				//创建持久化que队列
				channel.queueDeclare(que.getName(), true, false, false, null);
				//绑定que exchange 关系
				channel.queueBind(que.getName(), que.getExchage(), que.getKey());
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				channel.close();
			}
		}
		
		return null;
    }
	
	
	@Bean
	public ErrorHandler errorHandler(){
		return new ConditionalRejectingErrorHandler(new GlobalFatalExceptionStrategy());
	}
	
	public static class GlobalFatalExceptionStrategy implements FatalExceptionStrategy{

		protected final Log logger = LogFactory.getLog(getClass());
		
		@Override
		public boolean isFatal(Throwable t) {
			if (t instanceof ListenerExecutionFailedException){
				//保存错误日志				
				try{
					ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
					//队列名称
					String queueName = lefe.getFailedMessage().getMessageProperties().getConsumerQueue();
					//routingkey
					String routingKey = lefe.getFailedMessage().getMessageProperties().getReceivedRoutingKey();
					//交换机名称
					String exchangeName = lefe.getFailedMessage().getMessageProperties().getReceivedExchange();
					//消息内容
					String content = messageBody(lefe.getFailedMessage().getBody(), Object.class);
					//具体的类名
					String className = lefe.getFailedMessage().getMessageProperties().getInferredArgumentType() == null ? "" : lefe.getFailedMessage().getMessageProperties().getInferredArgumentType().getTypeName();
					//异常原因
					String reason = lefe.toString();
					//保存信息
					System.out.println(queueName + routingKey + exchangeName + content + className + reason);
					
				}catch(Exception e){
					
				}				
			}
			return true;
		}
		
	}
	
	private static String messageBody(byte[] body, Class<?> targetClass) throws JsonParseException, JsonMappingException, IOException{

		String contentAsString = new String(body, "UTF-8");
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		return JSONObject.toJSONString(jsonObjectMapper.readValue(contentAsString, jsonObjectMapper.constructType(targetClass)));
	}
}
