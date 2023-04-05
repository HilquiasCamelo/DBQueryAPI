package com.hilquiascamelo.dbqueryapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "escolaridade")
public class Escolaridade {


    /**
     * @author HilquiasCamelo"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escolaridade")
    private Long id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nivel")
    private String nivel;

    public Escolaridade ( Long id ,
                          boolean status ,
                          String descricao ,
                          String nivel ) {
        this.id = id;
        this.status = status;
        this.descricao = descricao;
        this.nivel = nivel;
    }

    public Escolaridade() {
        // default constructor
    }

    public Long getId ( ) {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public boolean isAtivo ( ) {
        return status;
    }

    public void setAtivo ( boolean ativo ) {
        this.status = ativo;
    }

    public String getDescricao ( ) {
        return descricao;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public String getNivel ( ) {
        return nivel;
    }

    public void setNivel ( String status ) {
        this.nivel = status;
    }
}
