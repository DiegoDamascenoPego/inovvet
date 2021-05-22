package com.inovvet.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cidade")
@Getter
@Setter
public class Cidade extends SimpleEntity {

	private String nome;

	private String uf;

	@Column(name = "codigo")
	private Integer codigo;

}
