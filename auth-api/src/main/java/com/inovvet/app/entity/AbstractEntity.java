package com.inovvet.app.entity;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity extends SimpleEntity {

	@JsonIgnoreProperties
	protected Integer conta_id;

}