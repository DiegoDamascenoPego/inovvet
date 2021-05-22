package com.inovvet.app.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String conta_token;
	
	private String loja_token;

	private String accessToken;
	
	private String nome;
	
	private String email;
	
	private String perfil;
	
	private List<GrantedAuthority> authorities;
	
	

	

}
