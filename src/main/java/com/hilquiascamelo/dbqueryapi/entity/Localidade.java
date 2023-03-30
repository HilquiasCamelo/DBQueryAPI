package com.hilquiascamelo.dbqueryapi.entity;

import javax.persistence.*;

@Entity
@Table (name = "localidade", schema = "Mrh")
public class Localidade {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long  idLocalidade;
    private boolean ativo;
    private String descricao;
    private String sigla;
    private String tipo;

    public Long getIdLocalidade ( ) {
        return idLocalidade;
    }

    public void setIdLocalidade ( Long idLocalidade ) {
        this.idLocalidade = idLocalidade;
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

    public String getSigla ( ) {
        return sigla;
    }

    public void setSigla ( String sigla ) {
        this.sigla = sigla;
    }

    public String getTipo ( ) {
        return tipo;
    }

    public void setTipo ( String tipo ) {
        this.tipo = tipo;
    }
}

