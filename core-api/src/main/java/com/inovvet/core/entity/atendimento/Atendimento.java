package com.inovvet.core.entity.atendimento;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.animal.Animal;
import com.inovvet.core.entity.cliente.Cliente;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.usuario.Usuario;
import com.inovvet.core.enumerator.EnumStatusAtendimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Atendimento extends AbstractEntity {
	
	@NotNull(message = "Loja deve ser informada.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
	private Loja loja;
	
	@NotNull(message = "Tipo Atendimento deve ser informado.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_atendimento_id")
	private TipoAtendimento tipoAtendimento;
	
	@NotNull(message = "Cliente deve ser informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
	private String observacao;
	
	@NotNull(message = "Data do Atendimento deve ser informado")
	private LocalDateTime data;
	
	@NotNull(message = "Usuário do Atendimento deve ser informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atendente_id")
	private Usuario atendente;
	
	@Embedded
	private Endereco endereco;
	
	private String telefone;
		
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private EnumStatusAtendimento status;

}
