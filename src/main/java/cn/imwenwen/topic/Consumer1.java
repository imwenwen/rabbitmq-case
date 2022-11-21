package cn.imwenwen.topic;


import cn.imwenwen.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 卢意
 * @create 2020-11-25 21:08
 */
public class Consumer1 {
	public static void main(String[] args) throws IOException {
		// 连接对象
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();

		// 通道绑定交换机
		channel.exchangeDeclare("topics","topic");

		// 临时队列
		String queue = channel.queueDeclare().getQueue();

		// 通过动态通配符router key 绑定交换机和队列
		channel.queueBind(queue, "topics", "user.#");// 参数三 路由key

		// 消费消息
		channel.basicConsume(queue, true,new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者1: "+new String(body));
			}
		});
	}

}

