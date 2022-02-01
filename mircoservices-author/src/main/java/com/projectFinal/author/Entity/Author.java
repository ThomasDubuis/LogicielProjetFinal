package com.projectFinal.author.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	@GeneratedValue
	public Integer id;
	public String pseudo;
}
