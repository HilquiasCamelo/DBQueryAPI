package com.hilquiascamelo.dbqueryapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "escolaridade")
public class Escolaridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escolaridade")
    private Long id;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private String status;

    // getters e setters omitidos para brevidade

}
