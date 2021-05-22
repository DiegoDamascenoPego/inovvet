package com.inovvet.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@CreatedDate
	@Column(name = "data_cadastro")
	@Exclude
	private LocalDateTime dataCadastro;

	@LastModifiedDate
	@Column(name = "data_atualizacao")
	@Exclude
	private LocalDateTime dataAtualizacao;

	@PrePersist
	public void beforeInsert() {
		System.out.println(LocalDateTime.now().toString());
		if (this.id == null) {
			this.setDataCadastro(LocalDateTime.now());
		}
		this.setDataAtualizacao(LocalDateTime.now());

	}

	@PreUpdate
	public void beforeUpdate() {
		
		if (this.id != null) {
			this.setDataAtualizacao(LocalDateTime.now());
		}

	}

}