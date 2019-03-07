package com.naturals.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="authorities")
@EqualsAndHashCode(of = "username")
@ToString
public class Authorities {
	
	@Id
	private String username;
	private String rolenm;
}
