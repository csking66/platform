package com.rabbitmq.controller;

import java.time.LocalDate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.common.enums.RabbitMQ;
import com.rabbitmq.callback.MQConfirmCallback;
import com.rabbitmq.callback.MQReturnCallback;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @ClassName: ConsumerCotroller
* @Description: 
* @date 2018年12月6日
*
*/
@Api(value = "MQ发送端确认")
@RestController
public class ConsumerCotroller {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private MQConfirmCallback confirmCallback;
	
	@Autowired
	private MQReturnCallback returnCallback;
	
	@ApiOperation(value = "发送que", notes = "发送que")
	@PostMapping("/rabbitmq/que")
	public void handlerQue() throws Exception {
		String context = "你好,现在时间是 " + LocalDate.now() + "";
		CorrelationData correlationData = new CorrelationData("2345");
		rabbitTemplate.setMandatory(true);//决定消息持久化
		rabbitTemplate.setReturnCallback(returnCallback);
		rabbitTemplate.setConfirmCallback(confirmCallback);		
		rabbitTemplate.convertAndSend(RabbitMQ.SYNC_DATA.getExchage(), RabbitMQ.SYNC_DATA.getKey(), context, correlationData);
	}
	
	
}
