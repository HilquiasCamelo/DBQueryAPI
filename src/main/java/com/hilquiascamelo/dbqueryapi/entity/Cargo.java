package com.hilquiascamelo.dbqueryapi.entity;

import javax.persistence.*;

@Entity
@Table ( name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id_cargo;

    private boolean ativo;
    private String descricao;
    private int ordem;
    private String sigla;

    public Long getIdCargo ( ) {
        return id_cargo;
    }

    public void setIdCargo ( Long id_cargo ) {
        this.id_cargo = id_cargo;
    }

    public boolean isAtivo ( ) {
        return ativo;
    }

    public void setAtivo ( boolean ativo ) {
        this.ativo = ativo;
    }

    public String getDescricao ( ) {
        return descricao;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public int getOrdem ( ) {
        return ordem;
    }

    public void setOrdem ( int ordem ) {
        this.ordem = ordem;
    }

    public String getSigla ( ) {
        return sigla;
    }

    public void setSigla ( String sigla ) {
        this.sigla = sigla;
    }
}
