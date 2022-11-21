package cn.imwenwen.workercase;

import cn.imwenwen.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Consumer2 {
	public static void main(String[] args) throws IOException {
		// 获取连接
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("work",true,false,false,null);

		channel.basicConsume("work", true,new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者-2: "+new String(body));
				//手动确认 发送ack 代表当前消息消费结束  可以去消费下一个消息
					channel.basicAck(envelope.getDeliveryTag(), false);
			}
		});
	}
}

