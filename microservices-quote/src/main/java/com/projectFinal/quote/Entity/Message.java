package com.projectFinal.quote.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	public Integer id;
	public String message;
	public String authorName;
	public Integer quoteId;
}
