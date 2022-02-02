package com.projectFinal.author.Repository;

import org.springframework.data.repository.CrudRepository;

import com.projectFinal.author.Entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer>{
	
	Iterable<Author> findByPseudo(String pseudo);
}
