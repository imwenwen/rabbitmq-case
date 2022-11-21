package cn.imwenwen.direct;

import cn.imwenwen.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;


public class Provider {
	public static void main(String[] args) throws IOException {
		// 获取连接
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();

		// 将通道声明指定交换机  参数1: 交换机的名称  参数2 交换机的类型
		channel.exchangeDeclare("logs_direct", "direct");

		// 发送消息
		String routingKey = "warning"; // 路由key  warning  info  error
		channel.basicPublish("logs_direct", routingKey, null, ("direct type message routingKey="+routingKey).getBytes());

		// 关闭资源
		RabbitMQUtil.closeConnection(channel, connection);
	}
}

