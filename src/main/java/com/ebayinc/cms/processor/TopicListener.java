package com.ebayinc.cms.processor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TopicListener implements MessageListener {

	private static Logger LOG = LoggerFactory.getLogger(TopicListener.class);

	@Autowired
	IMessageProcessor messageProcessor;

	@Autowired
	@Qualifier("consumerId")
	String consumerId;

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		LOG.debug("Received Message : {}", textMessage);
		try {
			String messageId = textMessage.getJMSMessageID();
			String text = textMessage.getText();
			boolean lock = messageProcessor.tryLock(messageId, consumerId);
			if (lock) {
				String msg = messageProcessor
						.matchMetadataAndFetchMessage(text);
				// send message to pipeline queue
				LOG.info("Processed Message : {}", msg);
				messageProcessor.unlock(messageId);
			}
		} catch (JMSException ex) {
			LOG.error("Error while processing message", ex);
		}
	}

}
