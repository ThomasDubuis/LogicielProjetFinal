package com.projectFinal.gateway.Repository;

import com.projectFinal.gateway.Entity.Message;

public interface MessageRepository {

	Message addMessage (Message message);
	
	Iterable<Message> getMessages (Integer quoteId);
	
	Integer deleteMessage (Integer messageId);
}
