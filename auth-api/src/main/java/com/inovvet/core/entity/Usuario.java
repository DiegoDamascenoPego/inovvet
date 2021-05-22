package com.inovvet.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.inovvet.app.entity.SimpleEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends SimpleEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	
	@Column(name = "email")
	private String username;
	
	private String password;
	
	private boolean accountNonExpired;
	
	private boolean accountNonLocked;
	
	private boolean credentialsNonExpired;
	
	private boolean enabled;

	
}
