package cn.iccspace.spring.queue;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mq-queue.xml"})
public class QueueReceiver {
  @Resource
  private JmsTemplate jmsTemplate;
  @Test
  public void receiveMqMessage(){
    Destination destination = jmsTemplate.getDefaultDestination();
    receive(destination);
  }
  /**
   * 接受消息
   */
  public void receive(Destination destination) {
    TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
    try {
      System.out.println("从队列" + destination.toString() + "收到了消息：\t" + tm.getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
  public void setJmsTemplate(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }
}