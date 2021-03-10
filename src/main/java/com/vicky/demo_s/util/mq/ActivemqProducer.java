package com.vicky.demo_s.util.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActivemqProducer {
    public static void main(String[] args) {
        ActivemqProducer activemqProducer = new ActivemqProducer();
        activemqProducer.send();
    }

    public void send(){
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        try {
            connection = factory.createConnection();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("myQueue");
            producer = session.createProducer(destination);
            Message message = null;
            for (int i=0;i<3;i++){
                message = session.createTextMessage("发送消息" + i);
                producer.send(message);
            }
        } catch (JMSException e) {
            try {
                if(producer != null){
                    producer.close();
                }
                if(session != null){
                    session.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (JMSException jmsException) {
                jmsException.printStackTrace();
            }
        }
    }
}
