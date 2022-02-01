package com.projectFinal.gateway.Repository;



import com.projectFinal.gateway.Entity.Author;

public interface AuthorRepository {

	Author addAuthor( Author author);
	
	Iterable<Author> getAuthors(String pseudo);
	
	Integer deleteAuthor(Integer authorId);
}
