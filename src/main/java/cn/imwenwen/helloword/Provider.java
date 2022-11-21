package cn.imwenwen.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;


import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Provider {
    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
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

        //发布消息
        // 参数  交换名称
        //      对接名称
        //      传递参数的额外设置
        //      消息的具体内容
        channel.basicPublish("","imwenwenMQ" , null,"imwenwen study mq".getBytes());

        channel.close();

        System.out.println("生产消息成功");
    }
}

