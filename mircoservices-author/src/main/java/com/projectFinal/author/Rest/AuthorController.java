package com.projectFinal.author.Rest;

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

import com.projectFinal.author.Entity.Author;
import com.projectFinal.author.Repository.AuthorRepository;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

	@Autowired
	AuthorRepository authorRepository;
	
	@PostMapping("/")
	public ResponseEntity<Author> addAuthor (@RequestBody Author author) {
		authorRepository.save(author);
		return ResponseEntity.status(HttpStatus.CREATED).body(author);
	}
	
	@GetMapping("/")
	public ResponseEntity<Iterable<Author>> getAuthors(@RequestParam(value="pseudo",required= false) String pseudo) {
		if(pseudo == null || pseudo == "") {
			//TODO: Ajouter les quotes lié a l'auteur
			return ResponseEntity.ok(authorRepository.findAll());
		}
		
		//TODO: Ajouter les quotes lié a l'auteur
		return ResponseEntity.ok(authorRepository.findByPseudo(pseudo));
	}
	
	@DeleteMapping("/{authorId}")
	public Integer deleteAuthors(@PathVariable("authorId") Integer authorId) {
		authorRepository.deleteById(authorId);
		return authorId;
	}
	//TODO: Faire le ratio des like / dislike
	
}
