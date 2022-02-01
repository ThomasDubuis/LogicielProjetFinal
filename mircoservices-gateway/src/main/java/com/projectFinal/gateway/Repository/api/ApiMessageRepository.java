package com.projectFinal.gateway.Repository.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectFinal.gateway.Entity.Message;
import com.projectFinal.gateway.Repository.MessageRepository;

@FeignClient(url = "http://localhost:8092/api/message", name = "message-api")
public interface ApiMessageRepository extends MessageRepository{
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Message addMessage (@RequestBody Message message);
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Message> getMessages (@RequestParam(value="quoteId", required = false) Integer quoteId);
	
	@RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE, produces = "application/json")
	public Integer deleteMessage (@PathVariable("messageId") Integer messageId);
}
