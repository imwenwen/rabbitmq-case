package cn.imwenwen.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitMQUtil {

	private static ConnectionFactory factory;

	static {
		factory = new ConnectionFactory();
		factory.setHost("106.15.170.237");
		factory.setPort(5672);
		factory.setVirtualHost("/rabbitmqCase");
		factory.setUsername("imwenwen");
		factory.setPassword("Cww2021");
	}

	/**
	 * 获取RabbitMQ的连接
	 * @return 连接对象
	 */
	public static Connection getConnection(){
		try {
			Connection connection = factory.newConnection();
			return connection;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭连接   注意：一般消费者不用关闭连接 只用于生产者关闭连接
	 * @param channel
	 * @param connection
	 */
	public static void closeConnection(Channel channel, Connection connection){
		try {
			if (channel != null){
				channel.close();
			}
			if (connection != null){
				connection.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

