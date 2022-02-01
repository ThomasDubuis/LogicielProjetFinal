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

import com.projectFinal.quote.Entity.Quote;
import com.projectFinal.quote.Repository.QuoteRepository;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {
	
	@Autowired
	QuoteRepository quoteRepository;
	
	@PostMapping("/")
	public ResponseEntity<Quote> addQuote (@RequestBody Quote quote) {
		if (quote.liked  == null) quote.liked = 0;
		if (quote.disliked  == null) quote.disliked = 0;
		
		quoteRepository.save(quote);
		return ResponseEntity.status(HttpStatus.CREATED).body(quote);
	}
	
	@GetMapping("/")
	public ResponseEntity<Iterable<Quote>> getQuotes(@RequestParam(value="authorName", required = false) String authorName) {
		if( authorName == null || authorName == "") {
			//TODO: retrouner aussi les messages des quotes
			return ResponseEntity.ok(quoteRepository.findAll());			
		}
		//TODO: retrouner aussi les messages des quotes
		return ResponseEntity.ok(quoteRepository.findByAuthorName(authorName));
	}
	
	@DeleteMapping("/{quoteId}")
	public Integer deleteAuthors(@PathVariable("quoteId") Integer quoteId) {
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
	
	
	  @GetMapping("/topquote") public ResponseEntity<Iterable<Quote>> getTopQuote()
	  { 
		  return ResponseEntity.ok(quoteRepository.findAllOrderByLikedAsc());
	  }
	 

}
