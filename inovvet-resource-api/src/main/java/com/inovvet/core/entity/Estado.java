package com.inovvet.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "uf")
@Getter
@Setter
public class Estado {
	
	@Id
	private String sigla;

	private String nome;
	
	private Integer codigo;

}
