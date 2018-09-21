package rabbitmq.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rabbitmq.returnCallback.MQConfirmCallback;
import rabbitmq.returnCallback.MQReturnCallback;

import com.domain.enums.RabbitMQ;

@Api(value = "MQ/RPC  调用")
@RestController
public class MQRpc {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private MQConfirmCallback confirmCallback;
	
	@Autowired
	private MQReturnCallback returnCallback;
	
	
	@ApiOperation(value = "发送que", notes = "发送que")
	@PostMapping("/rabbitmq/rpc")
	public void handlerQue() throws Exception {
		StringBuffer context = new StringBuffer();	
		context.append("你好,现在时间是 " + LocalDate.now() + "");
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnCallback(returnCallback);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		//rabbitTemplate.setExchange(RabbitMQ.QUE_EXCHANGE);
		//rabbitTemplate.setRoutingKey(RabbitMQ.REAULT.getKey());
		//rabbitTemplate.setReplyAddress(RabbitMQ.QUE_EXCHANGE + "/" + RabbitMQ.REAULT.getKey());
		//rabbitTemplate.setReplyAddress(RabbitMQ.REAULT.getKey());
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());		
		Object response = rabbitTemplate.convertSendAndReceive(RabbitMQ.NOTICE_A.getExchage(), RabbitMQ.NOTICE_A.getKey(), context, correlationData);
		System.out.println("消费者响应 : " + response + " 消息处理完成");
	}
}
