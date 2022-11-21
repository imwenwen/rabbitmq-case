package cn.imwenwen.direct;


import cn.imwenwen.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Consumer2 {
	public static void main(String[] args) throws IOException {
		// 连接对象
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();

		// 通道绑定交换机
		channel.exchangeDeclare("logs_direct","direct");

		// 临时队列
		String queue = channel.queueDeclare().getQueue();

		// 通过router key 绑定交换机和队列
		channel.queueBind(queue, "logs_direct", "info");// 参数三 路由key
		channel.queueBind(queue, "logs_direct", "error");// 参数三 路由key
		channel.queueBind(queue, "logs_direct", "warning");// 参数三 路由key

		// 消费消息
		channel.basicConsume(queue, true,new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者2: "+new String(body));
			}
		});
	}

}

