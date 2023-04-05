package com.hilquiascamelo.dbqueryapi.entity;


import javax.persistence.*;


/**
 * @author HilquiasCamelo"
 */
@Entity
@Table(name = "estado_civil")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_civil")
    private Long idEstadoCivil;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "descricao")
    private String descricao;

    public EstadoCivil ( Long idEstadoCivil ,
                         boolean ativo ,
                         String descricao ) {
        this.idEstadoCivil = idEstadoCivil;
        this.ativo = ativo;
        this.descricao = descricao;
    }

    public Long getIdEstadoCivil ( ) {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil ( Long idEstadoCivil ) {
        this.idEstadoCivil = idEstadoCivil;
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
}
