package rabbitmq.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rabbitmq.returnCallback.MQConfirmCallback;
import rabbitmq.returnCallback.MQReturnCallback;

import com.domain.enums.RabbitMQ;

@Api(value = "MQ发送端确认")
@RestController
public class Consumer{
	
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
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnCallback(returnCallback);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.convertAndSend(RabbitMQ.SYNC_DATA.getKey(), context);
	}
	
	@ApiOperation(value = "发送topic", notes = "发送topic")
	@PostMapping("/rabbitmq/exchange")
	public void handlerExchange() throws Exception {
		
	
	}

	
	
	

}
