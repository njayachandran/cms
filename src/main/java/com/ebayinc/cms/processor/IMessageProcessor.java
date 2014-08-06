package com.ebayinc.cms.processor;

public interface IMessageProcessor {

	/**
	 * This method is for locking message, so that message will be processed by
	 * only one listener in multiple consumer - scaled environment
	 * 
	 * @param messageId
	 * @param consumerId
	 * @return
	 */
	public Boolean tryLock(String messageId, String consumerId);

	/**
	 * This method is a mock method to get the assembled message for pipeline_q
	 * 
	 * @param message
	 * @return assembled message
	 */
	public String matchMetadataAndFetchMessage(String message);
	
	/**
	 * Unlock the message Id, so that recovery will not fetch it again
	 * 
	 * @param messageId
	 */
	public void unlock(String messageId);
	
	/**
	 * This method is to recover unstable data to be re - process
	 * called only at startup
	 * 
	 * @param consumerId
	 */
	public void runRecovery(String consumerId);
}
