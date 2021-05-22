package com.inovvet.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEndereco extends AbstractEntity {

	private String cep;

    private String rua;

    private String numero;

    private String bairro;

    private String complemento;

    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}
