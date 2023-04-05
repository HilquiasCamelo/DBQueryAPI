package com.hilquiascamelo.dbqueryapi.entity.dto;

import com.hilquiascamelo.dbqueryapi.entity.*;


public class PessoaFromDTO {
    private Cargo cargo;

    private String codigoReferencia;

    private String cpf;

    private String dataDeIngresso;

    private String dataDeNascimento;

    private boolean doadorDeOrgaos;

    private boolean doadorDeSangue;

    private Escolaridade escolaridade;

    private EstadoCivil estadoCivil;

    private String fatorRH;

    private String fatorSanguineo;

    private int id;

    private String matricula;

    private Localidade naturalidade;

    private String nomeCompleto;

    private String foto;

    private String apelido;

    private String nomeDaMae;

    private String nomeDoPai;

    private String observacoesAdicionais;

    private int oidCargoQuadroSgp;

    private int oidOmeSgp;

    private int oidPessoaSgp;

    private boolean pessoaValidada;

    private Quadro quadro;

    private Religiao religiao;

    private String rgCivil;

    private String sexo;

    public PessoaFromDTO ( Cargo cargo ,
                           String codigoReferencia ,
                           String cpf ,
                           String dataDeIngresso ,
                           String dataDeNascimento ,
                           boolean doadorDeOrgaos ,
                           boolean doadorDeSangue ,
                           Escolaridade escolaridade ,
                           EstadoCivil estadoCivil ,
                           String fatorRH ,
                           String fatorSanguineo ,
                           int id ,
                           String matricula ,
                           Localidade naturalidade ,
                           String nomeCompleto ,
                           String foto ,
                           String apelido ,
                           String nomeDaMae ,
                           String nomeDoPai ,
                           String observacoesAdicionais ,
                           int oidCargoQuadroSgp ,
                           int oidOmeSgp ,
                           int oidPessoaSgp ,
                           boolean pessoaValidada ,
                           Quadro quadro ,
                           Religiao religiao ,
                           String rgCivil ,
                           String sexo ) {
        this.cargo = cargo;
        this.codigoReferencia = codigoReferencia;
        this.cpf = cpf;
        this.dataDeIngresso = dataDeIngresso;
        this.dataDeNascimento = dataDeNascimento;
        this.doadorDeOrgaos = doadorDeOrgaos;
        this.doadorDeSangue = doadorDeSangue;
        this.escolaridade = escolaridade;
        this.estadoCivil = estadoCivil;
        this.fatorRH = fatorRH;
        this.fatorSanguineo = fatorSanguineo;
        this.id = id;
        this.matricula = matricula;
        this.naturalidade = naturalidade;
        this.nomeCompleto = nomeCompleto;
        this.foto = foto;
        this.apelido = apelido;
        this.nomeDaMae = nomeDaMae;
        this.nomeDoPai = nomeDoPai;
        this.observacoesAdicionais = observacoesAdicionais;
        this.oidCargoQuadroSgp = oidCargoQuadroSgp;
        this.oidOmeSgp = oidOmeSgp;
        this.oidPessoaSgp = oidPessoaSgp;
        this.pessoaValidada = pessoaValidada;
        this.quadro = quadro;
        this.religiao = religiao;
        this.rgCivil = rgCivil;
        this.sexo = sexo;
    }

    /**
     * Constructs a new object.
     */
    public PessoaFromDTO ( ) {
        super( );
    }

    public Cargo getCargo ( ) {
        return cargo;
    }

    public void setCargo ( Cargo cargo ) {
        this.cargo = cargo;
    }

    public String getCodigoReferencia ( ) {
        return codigoReferencia;
    }

    public void setCodigoReferencia ( String codigoReferencia ) {
        this.codigoReferencia = codigoReferencia;
    }

    public String getCpf ( ) {
        return cpf;
    }

    public void setCpf ( String cpf ) {
        this.cpf = cpf;
    }

    public String getDataDeIngresso ( ) {
        return dataDeIngresso;
    }

    public void setDataDeIngresso ( String dataDeIngresso ) {
        this.dataDeIngresso = dataDeIngresso;
    }

    public String getDataDeNascimento ( ) {
        return dataDeNascimento;
    }

    public void setDataDeNascimento ( String dataDeNascimento ) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public boolean isDoadorDeOrgaos ( ) {
        return doadorDeOrgaos;
    }

    public void setDoadorDeOrgaos ( boolean doadorDeOrgaos ) {
        this.doadorDeOrgaos = doadorDeOrgaos;
    }

    public boolean isDoadorDeSangue ( ) {
        return doadorDeSangue;
    }

    public void setDoadorDeSangue ( boolean doadorDeSangue ) {
        this.doadorDeSangue = doadorDeSangue;
    }

    public Escolaridade getEscolaridade ( ) {
        return escolaridade;
    }

    public void setEscolaridade ( Escolaridade escolaridade ) {
        this.escolaridade = escolaridade;
    }

    public EstadoCivil getEstadoCivil ( ) {
        return estadoCivil;
    }

    public void setEstadoCivil ( EstadoCivil estadoCivil ) {
        this.estadoCivil = estadoCivil;
    }

    public String getFatorRH ( ) {
        return fatorRH;
    }

    public void setFatorRH ( String fatorRH ) {
        this.fatorRH = fatorRH;
    }

    public String getFatorSanguineo ( ) {
        return fatorSanguineo;
    }

    public void setFatorSanguineo ( String fatorSanguineo ) {
        this.fatorSanguineo = fatorSanguineo;
    }

    public int getId ( ) {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getMatricula ( ) {
        return matricula;
    }

    public void setMatricula ( String matricula ) {
        this.matricula = matricula;
    }

    public Localidade getNaturalidade ( ) {
        return naturalidade;
    }

    public void setNaturalidade ( Localidade naturalidade ) {
        this.naturalidade = naturalidade;
    }

    public String getNomeCompleto ( ) {
        return nomeCompleto;
    }

    public void setNomeCompleto ( String nomeCompleto ) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getFoto ( ) {
        return foto;
    }

    public void setFoto ( String foto ) {
        this.foto = foto;
    }

    public String getApelido ( ) {
        return apelido;
    }

    public void setApelido ( String apelido ) {
        this.apelido = apelido;
    }

    public String getNomeDaMae ( ) {
        return nomeDaMae;
    }

    public void setNomeDaMae ( String nomeDaMae ) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getNomeDoPai ( ) {
        return nomeDoPai;
    }

    public void setNomeDoPai ( String nomeDoPai ) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getObservacoesAdicionais ( ) {
        return observacoesAdicionais;
    }

    public void setObservacoesAdicionais ( String observacoesAdicionais ) {
        this.observacoesAdicionais = observacoesAdicionais;
    }

    public int getOidCargoQuadroSgp ( ) {
        return oidCargoQuadroSgp;
    }

    public void setOidCargoQuadroSgp ( int oidCargoQuadroSgp ) {
        this.oidCargoQuadroSgp = oidCargoQuadroSgp;
    }

    public int getOidOmeSgp ( ) {
        return oidOmeSgp;
    }

    public void setOidOmeSgp ( int oidOmeSgp ) {
        this.oidOmeSgp = oidOmeSgp;
    }

    public int getOidPessoaSgp ( ) {
        return oidPessoaSgp;
    }

    public void setOidPessoaSgp ( int oidPessoaSgp ) {
        this.oidPessoaSgp = oidPessoaSgp;
    }

    public boolean isPessoaValidada ( ) {
        return pessoaValidada;
    }

    public void setPessoaValidada ( boolean pessoaValidada ) {
        this.pessoaValidada = pessoaValidada;
    }

    public Quadro getQuadro ( ) {
        return quadro;
    }

    public void setQuadro ( Quadro quadro ) {
        this.quadro = quadro;
    }

    public Religiao getReligiao ( ) {
        return religiao;
    }

    public void setReligiao ( Religiao religiao ) {
        this.religiao = religiao;
    }

    public String getRgCivil ( ) {
        return rgCivil;
    }

    public void setRgCivil ( String rgCivil ) {
        this.rgCivil = rgCivil;
    }

    public String getSexo ( ) {
        return sexo;
    }

    public void setSexo ( String sexo ) {
        this.sexo = sexo;
    }

    @Override
    public String toString ( ) {
        return "PessoaFromDTO{" +
               "cargo=" +
               cargo +
               ", codigoReferencia='" +
               codigoReferencia +
               '\'' +
               ", cpf='" +
               cpf +
               '\'' +
               ", dataDeIngresso='" +
               dataDeIngresso +
               '\'' +
               ", dataDeNascimento='" +
               dataDeNascimento +
               '\'' +
               ", doadorDeOrgaos=" +
               doadorDeOrgaos +
               ", doadorDeSangue=" +
               doadorDeSangue +
               ", escolaridade=" +
               escolaridade +
               ", estadoCivil=" +
               estadoCivil +
               ", fatorRH='" +
               fatorRH +
               '\'' +
               ", fatorSanguineo='" +
               fatorSanguineo +
               '\'' +
               ", id=" +
               id +
               ", matricula='" +
               matricula +
               '\'' +
               ", naturalidade=" +
               naturalidade +
               ", nomeCompleto='" +
               nomeCompleto +
               '\'' +
               ", foto='" +
               foto +
               '\'' +
               ", apelido='" +
               apelido +
               '\'' +
               ", nomeDaMae='" +
               nomeDaMae +
               '\'' +
               ", nomeDoPai='" +
               nomeDoPai +
               '\'' +
               ", observacoesAdicionais='" +
               observacoesAdicionais +
               '\'' +
               ", oidCargoQuadroSgp=" +
               oidCargoQuadroSgp +
               ", oidOmeSgp=" +
               oidOmeSgp +
               ", oidPessoaSgp=" +
               oidPessoaSgp +
               ", pessoaValidada=" +
               pessoaValidada +
               ", quadro=" +
               quadro +
               ", religiao=" +
               religiao +
               ", rgCivil='" +
               rgCivil +
               '\'' +
               ", sexo='" +
               sexo +
               '\'' +
               '}';
    }
}





