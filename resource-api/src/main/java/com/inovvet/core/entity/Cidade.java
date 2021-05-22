package com.inovvet.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cidade")
@Getter
@Setter
public class Cidade {
	
	@Id
	private String codigo;

	private String nome;

	private String uf;

	

}
