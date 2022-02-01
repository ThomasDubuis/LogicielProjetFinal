package com.projectFinal.quote.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectFinal.quote.Entity.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

	Iterable<Quote> findByAuthorName (String authorName);
	
	@Query(value = "SELECT * FROM QUOTE ORDER BY liked DESC LIMIT 10", nativeQuery = true)
	Iterable<Quote> findAllOrderByLikedAsc();
}
