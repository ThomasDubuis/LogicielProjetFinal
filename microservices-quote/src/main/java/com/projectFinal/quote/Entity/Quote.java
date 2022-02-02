package com.projectFinal.quote.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quote {

	@Id
	@GeneratedValue
	public Integer id;
	public String authorName;
	public String title;
	public Integer disliked;
	public Integer liked;
}
