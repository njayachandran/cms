package com.ebayinc.cms.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class RecoveryProcessor implements BeanFactoryPostProcessor {
	
	@Autowired
	private IMessageProcessor messageProcessor;
	
	@Autowired
	String consumerId;
	
	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		messageProcessor.runRecovery(consumerId);
	}
}
