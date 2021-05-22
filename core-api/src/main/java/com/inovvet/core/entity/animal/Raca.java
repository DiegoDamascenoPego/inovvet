package com.inovvet.core.entity.animal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.inovvet.app.entity.SimpleEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="raca")
@Cacheable(true)
public class Raca extends SimpleEntity {

	@Enumerated(EnumType.ORDINAL)
	private EnumTipoAnimal tipo;

	private String descricao;
}
