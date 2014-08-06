package com.ebayinc.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ebayinc.cms.exception.PropertyNotFoundException;

public class Driver {

	private static Logger LOG = LoggerFactory.getLogger(Driver.class);

	public static void main(String[] args) {
		String consumerId = System.getProperty("consumerId");
		if (consumerId == null) {
			LOG.error("Unable to load context due to missing property 'consumerId', Please set a unique name as a VM argument");
			throw new PropertyNotFoundException("consumerId");
		}
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/app-config.xml");
	}
}
