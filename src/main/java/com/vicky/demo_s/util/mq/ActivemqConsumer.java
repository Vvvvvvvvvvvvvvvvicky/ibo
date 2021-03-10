package com.vicky.demo_s.util.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class ActivemqConsumer {

    public static void main(String[] args) {
        ActivemqConsumer activemqConsumer = new ActivemqConsumer();
        activemqConsumer.receive();
    }

    public void receive() {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        try {
            connection = factory.createConnection();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("myQueue");
            consumer = session.createConsumer(destination);

            //同步阻塞式
//            while (true) {
//                TextMessage message = (TextMessage) consumer.receive();
//                if (message != null) {
//                    System.out.println("接收到消息：" + message.getText());
//                } else {
//                    break;
//                }
//            }
//            try {
//                if (consumer != null) {
//                    consumer.close();
//                }
//                if (session != null) {
//                    session.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (JMSException jmsException) {
//                jmsException.printStackTrace();
//            }

            //通过监听方式获取消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message != null && message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("获取到的消息是"+textMessage);
                    }
                }
            });
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JMSException jmsException) {
            jmsException.printStackTrace();
        }
    }
}
