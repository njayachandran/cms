package com.ebayinc.cms.data;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebayinc.cms.entity.MessageLock;

@Repository
public interface MessageLockData extends CrudRepository<MessageLock, Integer> {

	@Modifying
	@Transactional
	@Query("delete from MessageLock m where m.messageId = ?1")
	void deleteByMessageId(String messageId);

	List<MessageLock> findByMessageConsumerId(String consumerId);

}
