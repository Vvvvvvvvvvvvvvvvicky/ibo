package com.vicky.demo_s.util.io;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.InputStream;

public class BasicIO {
    public static void main(String[] agrs){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        try {
            Connection connection = activeMQConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue myQueue = session.createQueue("myQueue");
            //4.创建消息，此处创建了一个文本消息
            MessageProducer producer = session.createProducer(myQueue);

            for(int i=0; i<3;i++){
                producer.send(
                         session.createTextMessage("msg")
                );
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
