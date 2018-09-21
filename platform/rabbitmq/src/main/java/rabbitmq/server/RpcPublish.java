package rabbitmq.server;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.domain.enums.RabbitMQ;
import com.rabbitmq.client.Channel;

@Component
public class RpcPublish {

	@RabbitListener(queues = RabbitMQ.notice_a)
	@RabbitHandler
	public String process( String context,Channel channel, Message message) throws IOException {
		System.out.println("收到消息->" + context + "的时间" + LocalDate.now());
		message.getMessageProperties().getReplyTo();
		String result = "";
		  try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
			//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
			result = "消费成功";
			System.out.println("消费成功");
		} catch (IOException e) {			
			e.printStackTrace();
			//丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
			System.out.println("消费失败");
		}
		
		return result;
	}
}
