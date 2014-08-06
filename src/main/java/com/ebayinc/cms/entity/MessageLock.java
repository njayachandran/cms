package com.ebayinc.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "message_lock")
@NamedQuery(name = "MessageLock.findAll", query = "SELECT m FROM MessageLock m")
public class MessageLock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "message_id")
	private String messageId;

	@Column(name = "message_consumer_id")
	private String messageConsumerId;

	public MessageLock() {
		
	}
	
	public MessageLock(String messageId, String consumerId) {
		this.messageConsumerId = consumerId;
		this.messageId = messageId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageConsumerId() {
		return messageConsumerId;
	}

	public void setMessageConsumerId(String messageConsumerId) {
		this.messageConsumerId = messageConsumerId;
	}

	@Override
	public String toString() {
		return "MessageLock : { id : " + id + ", messageId : " + messageId
				+ ", consumerId : " + messageConsumerId + "}";
	}
}
