package com.hilquiascamelo.dbqueryapi.dto;

import com.hilquiascamelo.dbqueryapi.entity.Escolaridade;
import io.swagger.annotations.ApiModel;

import javax.persistence.Transient;
import javax.validation.constraints.Size;

@ApiModel ()
public class EscolaridadeDto extends AbstractDto < Long > {
    private Long id;

    @Transient
    private Long lastInsertedId;
    private boolean ativo;
    @Size ( max = 255)
    private String descricao;
    @Size ( max = 255)
    private String nivel;

    public EscolaridadeDto ( ) {
    }


    public EscolaridadeDto ( Long id ,
                             String descricao ,
                             String nivel ,
                             boolean ativo ) {
        super( );
    }

    public static EscolaridadeDto fromEntity( Escolaridade escolaridade) {
        EscolaridadeDto dto = new EscolaridadeDto();
        dto.setId(escolaridade.getId());
        dto.setDescricao(escolaridade.getDescricao());
        dto.setAtivo(escolaridade.isAtivo());

        return dto;
    }

    public Long getLastInsertedId ( ) {
        return lastInsertedId;
    }

    public void setLastInsertedId ( Long lastInsertedId ) {
        this.lastInsertedId = lastInsertedId;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Long getId ( ) {
        return this.id;
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

    public void setNivel ( String nivel ) {
        this.nivel = nivel;
    }

    public String getNivel ( ) {
        return this.nivel;
    }
}