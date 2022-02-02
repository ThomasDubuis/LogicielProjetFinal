package com.projectFinal.gateway.Repository.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectFinal.gateway.Entity.Quote;
import com.projectFinal.gateway.Entity.QuoteResponse;
import com.projectFinal.gateway.Repository.QuoteRepository;

@FeignClient(url = "http://localhost:8092/api/quote", name = "quote-api")
public interface ApiQuoteRepository extends QuoteRepository{

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Quote addQuote (@RequestBody Quote quote);
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public List<QuoteResponse> getQuotes (@RequestParam(value="authorName", required = false) String authorName);
	
	@RequestMapping(value = "/{quoteId}", method = RequestMethod.DELETE, produces = "application/json")
	public Integer deleteQuote (@PathVariable("quoteId")Integer quoteId);
	
	@RequestMapping(value = "/like", method = RequestMethod.GET, produces = "application/json")
	public Quote likeQuote (@RequestParam("quoteId") Integer quoteId);
	
	@RequestMapping(value = "/dislike", method = RequestMethod.GET, produces = "application/json")
	public Quote dislikeQuote (@RequestParam("quoteId") Integer quoteId);
	
	@RequestMapping(value = "/topquote", method = RequestMethod.GET, produces = "application/json")
	public List<QuoteResponse> getTopQuote ();
}
