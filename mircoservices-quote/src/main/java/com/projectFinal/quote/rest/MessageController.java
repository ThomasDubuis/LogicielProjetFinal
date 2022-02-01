package com.projectFinal.quote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectFinal.quote.Entity.Message;
import com.projectFinal.quote.Repository.MessageRepository;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	MessageRepository messageRepository;
	
	@PostMapping("/")
	public ResponseEntity<Message> addQuote (@RequestBody Message message) {
		messageRepository.save(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
	
	@GetMapping("/")
	public ResponseEntity<Iterable<Message>> getQuotes(@RequestParam(value="quoteId", required = false) Integer quoteId) {
		if( quoteId == null) {
			return ResponseEntity.ok(messageRepository.findAll());			
		}
		return ResponseEntity.ok(messageRepository.findByQuoteId(quoteId));
	}
	
	@DeleteMapping("/{messageId}")
	public Integer deleteAuthors(@PathVariable("messageId") Integer messageId) {
		messageRepository.deleteById(messageId);
		return messageId;
	}
	
}
