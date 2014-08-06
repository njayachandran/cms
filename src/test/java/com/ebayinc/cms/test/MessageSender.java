package com.ebayinc.cms.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class MessageSender {

	private static Logger LOG = LoggerFactory.getLogger(MessageSender.class);

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Topic topic;
	
	@Test
	public void testSendMessage() {
		sendMessage("Test Message");
	}
	
	public void sendMessage(final String message) {
		LOG.info("Sending Message : {}", message);
		jmsTemplate.send(topic, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		LOG.info("Message {} sent", message);
	}

}
