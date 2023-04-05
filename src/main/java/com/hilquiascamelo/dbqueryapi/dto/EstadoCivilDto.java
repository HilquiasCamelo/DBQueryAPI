package com.hilquiascamelo.dbqueryapi.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;

@ApiModel ()
public class EstadoCivilDto extends AbstractDto < Long > {
    private Long idEstadoCivil;
    private boolean ativo;
    @Size ( max = 255)
    private String descricao;

    public EstadoCivilDto ( ) {
    }

    public void setIdEstadoCivil ( Long idEstadoCivil ) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Long getIdEstadoCivil ( ) {
        return this.idEstadoCivil;
    }

    public void setAtivo ( boolean ativo ) {
        this.ativo = ativo;
    }

    public boolean getAtivo ( ) {
        return this.ativo;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public String getDescricao ( ) {
        return this.descricao;
    }
}