package com.rabbitmq.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Component;

@Component
public class MQReturnCallback implements ReturnCallback{

	/**
	 * 实现ReturnCallback
	 * 当消息发送出去找不到对应路由队列时，将会把消息退回
	 * 如果有任何一个路由队列接收投递消息成功，则不会退回消息
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		StringBuffer sb = new StringBuffer();
		sb.append("message -> ");
		sb.append(message.toString());
		
		sb.append("replyCode -> ");
		sb.append(replyCode);
		
		sb.append("replyText -> ");
		sb.append(replyText);
		
		sb.append("exchange -> ");
		sb.append(exchange);
		
		sb.append("routingKey -> ");
		sb.append(routingKey);
		
		System.out.println(sb.toString());
		
	}

}
