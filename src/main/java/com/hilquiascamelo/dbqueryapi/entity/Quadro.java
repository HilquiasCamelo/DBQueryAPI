package com.hilquiascamelo.dbqueryapi.entity;

import javax.persistence.*;

/**
 * @author HilquiasCamelo"
 */
@Entity
@Table (name = "quardo", schema = "Mrh")
public class Quadro {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long    idQuadro;
    private boolean ativo;
    private String  descricao;
    private String  sigla;
    private String  tipo;

    public Quadro ( Long idQuadro ,
                    boolean ativo ,
                    String descricao ,
                    String sigla ,
                    String tipo ) {
        this.idQuadro = idQuadro;
        this.ativo = ativo;
        this.descricao = descricao;
        this.sigla = sigla;
        this.tipo = tipo;
    }

    public Long getIdQuadro ( ) {
        return idQuadro;
    }

    public void setIdQuadro ( Long idQuadro ) {
        this.idQuadro = idQuadro;
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
