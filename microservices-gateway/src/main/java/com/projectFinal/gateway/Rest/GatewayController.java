package com.projectFinal.gateway.Rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projectFinal.gateway.Entity.Author;
import com.projectFinal.gateway.Entity.AuthorQuoteResponse;
import com.projectFinal.gateway.Entity.Message;
import com.projectFinal.gateway.Entity.Quote;
import com.projectFinal.gateway.Entity.Ratio;
import com.projectFinal.gateway.Entity.QuoteResponse;
import com.projectFinal.gateway.Repository.AuthorRepository;
import com.projectFinal.gateway.Repository.MessageRepository;
import com.projectFinal.gateway.Repository.QuoteRepository;

@RestController
@RequestMapping("/api")
public class GatewayController {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	QuoteRepository quoteRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	//Author route
	@PostMapping("/author/")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
		return ResponseEntity.ok(authorRepository.addAuthor(author));
	}
	
	@GetMapping("/author/")
	public ResponseEntity<Iterable<Author>> getAuthors(@RequestParam(value="pseudo",required= false) String pseudo) {
		return ResponseEntity.ok(authorRepository.getAuthors(pseudo));
	}
	
	@DeleteMapping("/author/{authorId}")
	public ResponseEntity<Integer> deleteAuthors(@PathVariable("authorId") Integer authorId) {
		return ResponseEntity.ok(authorRepository.deleteAuthor(authorId));
	}
	
	@GetMapping("/author/getRatio/{pseudo}")
	public ResponseEntity<Ratio> getRatio(@PathVariable("pseudo") String pseudo) {
		Ratio ratio = new Ratio();
		Author author = authorRepository.getAuthors(pseudo).iterator().next();
		List<QuoteResponse> listQuote = quoteRepository.getQuotes(pseudo);
		ratio.id = author.id;
		ratio.pseudo = author.pseudo;
		ratio.totalDislike = 0;
		ratio.totalLike = 0;
		
		for(QuoteResponse quoteResponse : listQuote) {
			ratio.totalDislike += quoteResponse.disliked;
			ratio.totalLike += quoteResponse.liked;
		}
		
		return ResponseEntity.ok(ratio);
	}
	
	@GetMapping("/author/getQuoteAuthor/{pseudo}")
	public ResponseEntity<AuthorQuoteResponse> getQuoteAuthor (@PathVariable("pseudo") String pseudo) {
		AuthorQuoteResponse authorQuoteResponse = new AuthorQuoteResponse();
		authorQuoteResponse.pseudo = pseudo;
		authorQuoteResponse.quote = quoteRepository.getQuotes(pseudo);
		return ResponseEntity.ok(authorQuoteResponse);
	}
	
	//Quote route
	@PostMapping("/quote/")
	public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
		return ResponseEntity.ok(quoteRepository.addQuote(quote));
	}
	
	@GetMapping("/quote/")
	public ResponseEntity<List<QuoteResponse>> getQuotes(@RequestParam(value="authorName",required= false) String authorName) {
		return ResponseEntity.ok(quoteRepository.getQuotes(authorName));
	}
	
	@DeleteMapping("/quote/{quoteId}")
	public ResponseEntity<Integer> deleteQuote(@PathVariable("quoteId") Integer quoteId) {
		return ResponseEntity.ok(quoteRepository.deleteQuote(quoteId));
	}
	
	@GetMapping("/quote/like")
	public ResponseEntity<Quote> likeQuote (@RequestParam("quoteId") Integer quoteId) {
		return ResponseEntity.ok(quoteRepository.likeQuote(quoteId));
	}
	
	@GetMapping("/quote/dislike")
	public ResponseEntity<Quote> dislikeQuote (@RequestParam("quoteId") Integer quoteId) {
		return ResponseEntity.ok(quoteRepository.dislikeQuote(quoteId));
	}
	
	@GetMapping("/quote/topquote")
	public ResponseEntity<List<QuoteResponse>> getTopQuote() {
		return ResponseEntity.ok(quoteRepository.getTopQuote());
	}
	
	//Message Route
	@PostMapping("/message/")
	public ResponseEntity<Message> addMessage(@RequestBody Message message) {
		return ResponseEntity.ok(messageRepository.addMessage(message));
	}
	@GetMapping("/message/")
	public ResponseEntity<Iterable<Message>> getMessage(@RequestParam(value="quoteId",required= false) Integer quoteId) {
		return ResponseEntity.ok(messageRepository.getMessages(quoteId));
	}
	
	@DeleteMapping("/message/{messageId}")
	public ResponseEntity<Integer> deleteMessage(@PathVariable("messageId") Integer messageId) {
		return ResponseEntity.ok(messageRepository.deleteMessage(messageId));
	}
	
}
