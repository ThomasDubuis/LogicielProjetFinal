package com.projectFinal.quote.Repository;

import org.springframework.data.repository.CrudRepository;

import com.projectFinal.quote.Entity.Message;

public interface MessageRepository extends CrudRepository<Message, Integer>{

	Iterable<Message> findByQuoteId (Integer quoteId);
}
