package cn.imwenwen.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Consumer {
	// 消费消息
	@Test
	public void testGetMessage() throws IOException, TimeoutException {
		// 创建连接mq的连接工厂
		ConnectionFactory connectionFactory=new ConnectionFactory();
		// 设置链接rabbitmq主机
		connectionFactory.setHost("106.15.170.237");
		// 设置主机端口号
		connectionFactory.setPort(5672);
		// 设置连接哪个虚拟主机
		connectionFactory.setVirtualHost("/rabbitmqCase");
		// 设置访问虚拟主机的用户名和密码
		connectionFactory.setUsername("imwenwen");
		connectionFactory.setPassword("Cww2021");

		// 获取连接对象
		Connection connection =  connectionFactory.newConnection();

		// 通过连接获取连接中的通道对象
		Channel channel = connection.createChannel();

		// 通道绑定对应的消息队列
		// 参数  队列名称(不存在的时候自动创建)
		//	 	用来定义队列特征是要持久化
		//		是否独占队列(true 就只能被当前连接使用)
		// 		是否在消费完成后自动删除队列
		//      附加参数
		channel.queueDeclare("imwenwenMQ",false,false,false,null);

		// 消费消息
		//参数  消费哪个对列的消息 队列名称
		//     开启消息的自动确认机制
		//     消费时的回调接口
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel);

		channel.basicConsume("imwenwenMQ", true,defaultConsumer);
		channel.close();
		connection.close();

		System.out.println("消费消息成功");
	}
}

