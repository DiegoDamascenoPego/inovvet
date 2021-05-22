package com.inovvet.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.inovvet.app.entity.enumerator.TipoLog;
import com.inovvet.app.entity.enumerator.TipoObjeto;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "log")
@Getter
@Setter
public class Log extends SimpleEntity {

    private LocalDateTime data;

    @Enumerated(EnumType.ORDINAL)
    private TipoLog tipo;

    @Enumerated(EnumType.ORDINAL)
    private TipoObjeto objeto;

    private String mensagem;


    public Log(TipoLog tipo, TipoObjeto objeto, String mensagem) {
        this.tipo = tipo;
        this.objeto = objeto;
        this.mensagem = mensagem;
        data = LocalDateTime.now();
    }

    public Log(TipoObjeto objeto, String mensagem) {
        this.objeto = objeto;
        this.mensagem = mensagem;
        data = LocalDateTime.now();
    }

    public Log() {
        data = LocalDateTime.now();
    }
	
}
