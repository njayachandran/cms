package com.ebayinc.cms.exception;

public class PropertyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertyNotFoundException(String name) {
		super("Required property "+ name + " is missing");
	}

}
