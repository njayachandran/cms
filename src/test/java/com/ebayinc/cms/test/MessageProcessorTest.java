package com.ebayinc.cms.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ebayinc.cms.processor.IMessageProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class MessageProcessorTest {

	@Autowired
	IMessageProcessor messageProcessor;
	
	@Test
	public void testTryLock() {
		String tempMsgId = String.valueOf(new Date().getTime());
		String consumerId = "test";
		boolean lock = messageProcessor.tryLock(tempMsgId, consumerId);
		Assert.assertTrue(lock);
		lock = messageProcessor.tryLock(tempMsgId, "test2");
		Assert.assertFalse(lock);
		messageProcessor.unlock(tempMsgId);
	}

}
