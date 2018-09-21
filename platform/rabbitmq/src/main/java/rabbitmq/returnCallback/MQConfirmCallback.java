package rabbitmq.returnCallback;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class MQConfirmCallback implements ConfirmCallback{

	/**
	 * ACK=true仅仅标示消息已被Broker接收到，并不表示已成功投放至消息队列中
	 * ACK=false标示消息由于Broker处理错误，消息并未处理成功
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
            System.out.println("消息发送失败" + cause + correlationData.toString());
        } else {
            System.out.println("消息发送成功 ");
        }		
	}

}
