package com.projectFinal.quote.rest;

import java.util.ArrayList;
import java.util.List;

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

import com.projectFinal.quote.Entity.Quote;
import com.projectFinal.quote.EntityResponse.QuoteResponse;
import com.projectFinal.quote.Repository.MessageRepository;
import com.projectFinal.quote.Repository.QuoteRepository;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {
	
	@Autowired
	QuoteRepository quoteRepository;
	@Autowired
	MessageRepository messageRepository;
	
	@PostMapping("/")
	public ResponseEntity<Quote> addQuote (@RequestBody Quote quote) {
		if (quote.liked  == null) quote.liked = 0;
		if (quote.disliked  == null) quote.disliked = 0;
		
		quoteRepository.save(quote);
		return ResponseEntity.status(HttpStatus.CREATED).body(quote);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<QuoteResponse>> getQuotes(@RequestParam(value="authorName", required = false) String authorName) {
		List<QuoteResponse> quoteResponses = new ArrayList<QuoteResponse>();
		List<Quote> quotes;
		
		if( authorName == null || authorName == "") {
			quotes = quoteRepository.findAll();		
		}else {
			quotes = quoteRepository.findByAuthorName(authorName);
		}
		
		for(Quote quote : quotes ) {
			QuoteResponse quoteResponse = new QuoteResponse();
			
			quoteResponse.id = quote.id;
			quoteResponse.authorName = quote.authorName;
			quoteResponse.disliked = quote.disliked;
			quoteResponse.liked = quote.liked;
			quoteResponse.title = quote.title;
			quoteResponse.listMessage = messageRepository.findByQuoteId(quote.id);
			
			quoteResponses.add(quoteResponse);
		}
		
		return ResponseEntity.ok(quoteResponses);
	}
	
	@DeleteMapping("/{quoteId}")
	public Integer deleteQuote(@PathVariable("quoteId") Integer quoteId) {
		quoteRepository.deleteById(quoteId);
		return quoteId;
	}
	
	@GetMapping("/like")
	public ResponseEntity<Quote> likeQuote(@RequestParam("quoteId") Integer quoteId) {
		Quote quote = quoteRepository.findById(quoteId).get();
		quote.liked += 1;
		quoteRepository.save(quote);
		return ResponseEntity.ok(quote);
	}
	
	@GetMapping("/dislike")
	public ResponseEntity<Quote> dislikeQuote(@RequestParam("quoteId") Integer quoteId) {
		Quote quote = quoteRepository.findById(quoteId).get();
		quote.disliked += 1;
		quoteRepository.save(quote);
		return ResponseEntity.ok(quote);
	}
	
	
	  @GetMapping("/topquote") 
	  public ResponseEntity<List<QuoteResponse>> getTopQuote() { 
		  List<QuoteResponse> quoteResponses = new ArrayList<QuoteResponse>();
		  List<Quote> quotes;
		  quotes = quoteRepository.findAllOrderByLikedAsc();
		  for(Quote quote : quotes ) {
			  
			  QuoteResponse quoteResponse = new QuoteResponse();
				
			  quoteResponse.id = quote.id;
			  quoteResponse.authorName = quote.authorName;
			  quoteResponse.disliked = quote.disliked;
			  quoteResponse.liked = quote.liked;
			  quoteResponse.title = quote.title;
			  quoteResponse.listMessage = messageRepository.findByQuoteId(quote.id);
			
			  quoteResponses.add(quoteResponse);
		  }
			
		return ResponseEntity.ok(quoteResponses); 
	  }
	 

}
