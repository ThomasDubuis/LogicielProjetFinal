package com.projectFinal.gateway.Repository;

import java.util.List;

import com.projectFinal.gateway.Entity.Quote;
import com.projectFinal.gateway.Entity.QuoteResponse;

public interface QuoteRepository {
	
	Quote addQuote (Quote quote);
	
	List<QuoteResponse> getQuotes (String authorName);

	Integer deleteQuote (Integer quoteId);
	
	Quote likeQuote (Integer quoteId);
	
	Quote dislikeQuote (Integer quoteId);
	
	List<QuoteResponse> getTopQuote ();
}
