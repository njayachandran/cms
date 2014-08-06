package com.ebayinc.cms.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.ebayinc.cms.data.MessageLockData;
import com.ebayinc.cms.entity.MessageLock;

@Component
public class MessageProcessor implements IMessageProcessor {

	private static Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

	@Autowired
	MessageLockData messageLockData;

	@Override
	public Boolean tryLock(String messageId, String consumerId) {
		try {
			LOG.info("Consumer : {}, Trying to acquire lock : {}", consumerId, messageId);
			MessageLock value = messageLockData.save(new MessageLock(messageId,
					consumerId));
			LOG.info("Lock Acquired : {}", value);
		} catch (DataIntegrityViolationException ex) {
			LOG.info("Unable to lock message for processing");
			return false;
		}
		return true;
	}

	@Override
	public String matchMetadataAndFetchMessage(String message) {
		LOG.info("Matching metadata");
		return "{ message : " + message + " }";
	}

	@Override
	public void unlock(String messageId) {
		messageLockData.deleteByMessageId(messageId);
		LOG.info("Unlocked : {}", messageId);
	}

	@Override
	public void runRecovery(String consumerId) {
		List<MessageLock> locks =  messageLockData.findByMessageConsumerId(consumerId);
		LOG.info("Running recovery for {}", consumerId);
		for (MessageLock lock : locks) {
			LOG.info(lock.getMessageId());
		}
		
	}

}
