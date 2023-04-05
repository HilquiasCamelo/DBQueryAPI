package com.hilquiascamelo.dbqueryapi.entity;


import javax.persistence.*;

/**
 * @author HilquiasCamelo"
 */
@Entity
@Table(name = "religiao", schema = "Mrh")
public class Religiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_religiao;

    private Boolean ativo;

    private String descricao;
    public Religiao ( Long id_religiao ,
                      Boolean ativo ,
                      String descricao ) {
        this.id_religiao = id_religiao;
        this.ativo = ativo;
        this.descricao = descricao;
    }

    public Long getId_religiao() {
        return id_religiao;
    }

    public void setId_religiao(Long id_religiao) {
        this.id_religiao = id_religiao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
