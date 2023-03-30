package com.hilquiascamelo.dbqueryapi.entity;


import javax.persistence.*;

@Entity
@Table(name = "estado_civil", schema = "Mrh")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_civil")
    private Long idEstadoCivil;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "descricao")
    private String descricao;

    // construtores, getters e setters
}
