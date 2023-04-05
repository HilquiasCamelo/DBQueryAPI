package com.hilquiascamelo.dbqueryapi.dto;

import io.swagger.annotations.ApiModel;

@ApiModel ()
public class ReligiaoDto extends AbstractDto < Long > {
    private Long id_religiao;
    private Boolean ativo;
    private String descricao;

    public ReligiaoDto ( ) {
    }

    public void setId_religiao ( Long id_religiao ) {
        this.id_religiao = id_religiao;
    }

    public Long getId_religiao ( ) {
        return this.id_religiao;
    }

    public void setAtivo ( Boolean ativo ) {
        this.ativo = ativo;
    }

    public Boolean getAtivo ( ) {
        return this.ativo;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public String getDescricao ( ) {
        return this.descricao;
    }
}