package com.inovvet.core.entity.pessoa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "PessoaEntity")
@Table(name="pessoa")
public class PessoaEntity extends Pessoa  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
