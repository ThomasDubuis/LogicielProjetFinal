package com.projectFinal.quote.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projectFinal.quote.Entity.Message;

public interface MessageRepository extends CrudRepository<Message, Integer>{

	List<Message> findByQuoteId (Integer quoteId);
}
