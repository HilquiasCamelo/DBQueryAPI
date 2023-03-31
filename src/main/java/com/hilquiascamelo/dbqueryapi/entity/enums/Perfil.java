package com.hilquiascamelo.dbqueryapi.entity.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT"),
    USER(3, "ROLE_USER");

    private int cod;
    private String descricao;

    private Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao () {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
