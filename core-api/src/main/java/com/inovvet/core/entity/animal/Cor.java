package com.inovvet.core.entity.animal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.inovvet.app.entity.SimpleEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cor")
public class Cor extends SimpleEntity {

	private String nome;
	
}
