package com.projectFinal.gateway.Repository.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectFinal.gateway.Entity.Author;
import com.projectFinal.gateway.Repository.AuthorRepository;

@FeignClient(url = "${feign.authorServiceURL}/api/author", name = "authors-api")
public interface ApiAuthorRepository extends AuthorRepository {

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Author addAuthor(@RequestBody Author author);
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Author> getAuthors(@RequestParam(value="pseudo",required= false) String pseudo);
	
	@RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE, produces = "application/json")
	public Integer deleteAuthor(@PathVariable("authorId") Integer authorId);
}
