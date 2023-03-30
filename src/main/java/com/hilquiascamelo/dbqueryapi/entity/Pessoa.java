package com.hilquiascamelo.dbqueryapi.entity;

import javax.persistence.*;

import java.time.LocalDate;


@Entity
@Table (schema = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_pessoa")
    private Long id;

    @Column (name = "cod_ref")
    private String codRef;

    @Column (name = "cpf")
    private String cpf;

    @Column (name = "data_ingresso_sgp")
    private LocalDate dataIngresso;

    @Column (name = "data_nasc")
    private LocalDate dataNascimento;

    @Column (name = "doador_orgao")
    private Boolean doadorOrgao;

    @Column (name = "doador_sanguineo")
    private Boolean doadorSanguineo;

    @Column (name = "fator_rh")
    private String fatorRH;

    @Column (name = "fator_sanguineo")
    private String fatorSanguineo;

    @Column (name = "matricula_sgp")
    private String matriculaSgp;

    @Column (name = "nome_completo")
    private String nomeCompleto;

    @Column (name = "nome_foto")
    private String nomeFoto;

    @Column (name = "nome_guerra_sgp")
    private String nomeGuerraSgp;

    @Column (name = "nome_mae")
    private String nomeMae;

    @Column (name = "nome_pai")
    private String nomePai;

    @Column (name = "obs_adicional")
    private String observacaoAdicional;

    @Column (name = "oid_cargo_quadro_sgp")
    private Long oidCargoQuadroSgp;

    @Column (name = "oid_ome_sgp")
    private Long oidOmeSgp;

    @Column (name = "oid_pessoa_sgp")
    private Long oidPessoaSgp;

    @Column (name = "pessoa_validado")
    private Boolean pessoaValidada;

    @Column (name = "rg_militar_sgp")
    private String rgMilitarSgp;

    @Column (name = "sexo")
    private String sexo;

    @ManyToOne
    @JoinColumn (name = "id_escolaridade")
    private Escolaridade escolaridade;

    @ManyToOne
    @JoinColumn (name = "id_estado_civil")
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn (name = "id_religiao")
    private Religiao religiao;

    @ManyToOne
    @JoinColumn (name = "oid_cargo_sgp")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn (name = "oid_quadro_sgp")
    private Quadro quadro;

    @ManyToOne
    @JoinColumn (name = "naturalidade")
    private Localidade naturalidade;

    // getters e setters

    public Pessoa ( Long id , String codRef , String cpf , LocalDate dataIngresso , LocalDate dataNascimento , Boolean doadorOrgao , Boolean doadorSanguineo , String fatorRH , String fatorSanguineo , String matriculaSgp , String nomeCompleto , String nomeFoto , String nomeGuerraSgp , String nomeMae , String nomePai , String observacaoAdicional , Long oidCargoQuadroSgp , Long oidOmeSgp , Long oidPessoaSgp , Boolean pessoaValidada , String rgMilitarSgp , String sexo , Escolaridade escolaridade , EstadoCivil estadoCivil , Religiao religiao , Cargo cargo , Quadro quadro , Localidade naturalidade ) {
        this.id = id;
        this.codRef = codRef;
        this.cpf = cpf;
        this.dataIngresso = dataIngresso;
        this.dataNascimento = dataNascimento;
        this.doadorOrgao = doadorOrgao;
        this.doadorSanguineo = doadorSanguineo;
        this.fatorRH = fatorRH;
        this.fatorSanguineo = fatorSanguineo;
        this.matriculaSgp = matriculaSgp;
        this.nomeCompleto = nomeCompleto;
        this.nomeFoto = nomeFoto;
        this.nomeGuerraSgp = nomeGuerraSgp;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.observacaoAdicional = observacaoAdicional;
        this.oidCargoQuadroSgp = oidCargoQuadroSgp;
        this.oidOmeSgp = oidOmeSgp;
        this.oidPessoaSgp = oidPessoaSgp;
        this.pessoaValidada = pessoaValidada;
        this.rgMilitarSgp = rgMilitarSgp;
        this.sexo = sexo;
        this.escolaridade = escolaridade;
        this.estadoCivil = estadoCivil;
        this.religiao = religiao;
        this.cargo = cargo;
        this.quadro = quadro;
        this.naturalidade = naturalidade;
    }


    public Long getId ( ) {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodRef ( ) {
        return codRef;
    }

    public void setCodRef ( String codRef ) {
        this.codRef = codRef;
    }

    public String getCpf ( ) {
        return cpf;
    }

    public void setCpf ( String cpf ) {
        this.cpf = cpf;
    }

    public LocalDate getDataIngresso ( ) {
        return dataIngresso;
    }

    public void setDataIngresso ( LocalDate dataIngresso ) {
        this.dataIngresso = dataIngresso;
    }

    public LocalDate getDataNascimento ( ) {
        return dataNascimento;
    }

    public void setDataNascimento ( LocalDate dataNascimento ) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getDoadorOrgao ( ) {
        return doadorOrgao;
    }

    public void setDoadorOrgao ( Boolean doadorOrgao ) {
        this.doadorOrgao = doadorOrgao;
    }

    public Boolean getDoadorSanguineo ( ) {
        return doadorSanguineo;
    }

    public void setDoadorSanguineo ( Boolean doadorSanguineo ) {
        this.doadorSanguineo = doadorSanguineo;
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

    public String getMatriculaSgp ( ) {
        return matriculaSgp;
    }

    public void setMatriculaSgp ( String matriculaSgp ) {
        this.matriculaSgp = matriculaSgp;
    }

    public String getNomeCompleto ( ) {
        return nomeCompleto;
    }

    public void setNomeCompleto ( String nomeCompleto ) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeFoto ( ) {
        return nomeFoto;
    }

    public void setNomeFoto ( String nomeFoto ) {
        this.nomeFoto = nomeFoto;
    }

    public String getNomeGuerraSgp ( ) {
        return nomeGuerraSgp;
    }

    public void setNomeGuerraSgp ( String nomeGuerraSgp ) {
        this.nomeGuerraSgp = nomeGuerraSgp;
    }

    public String getNomeMae ( ) {
        return nomeMae;
    }

    public void setNomeMae ( String nomeMae ) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai ( ) {
        return nomePai;
    }

    public void setNomePai ( String nomePai ) {
        this.nomePai = nomePai;
    }

    public String getObservacaoAdicional ( ) {
        return observacaoAdicional;
    }

    public void setObservacaoAdicional ( String observacaoAdicional ) {
        this.observacaoAdicional = observacaoAdicional;
    }

    public Long getOidCargoQuadroSgp ( ) {
        return oidCargoQuadroSgp;
    }

    public void setOidCargoQuadroSgp ( Long oidCargoQuadroSgp ) {
        this.oidCargoQuadroSgp = oidCargoQuadroSgp;
    }

    public Long getOidOmeSgp ( ) {
        return oidOmeSgp;
    }

    public void setOidOmeSgp ( Long oidOmeSgp ) {
        this.oidOmeSgp = oidOmeSgp;
    }

    public Long getOidPessoaSgp ( ) {
        return oidPessoaSgp;
    }

    public void setOidPessoaSgp ( Long oidPessoaSgp ) {
        this.oidPessoaSgp = oidPessoaSgp;
    }

    public Boolean getPessoaValidada ( ) {
        return pessoaValidada;
    }

    public void setPessoaValidada ( Boolean pessoaValidada ) {
        this.pessoaValidada = pessoaValidada;
    }

    public String getRgMilitarSgp ( ) {
        return rgMilitarSgp;
    }

    public void setRgMilitarSgp ( String rgMilitarSgp ) {
        this.rgMilitarSgp = rgMilitarSgp;
    }

    public String getSexo ( ) {
        return sexo;
    }

    public void setSexo ( String sexo ) {
        this.sexo = sexo;
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

    public Religiao getReligiao ( ) {
        return religiao;
    }

    public void setReligiao ( Religiao religiao ) {
        this.religiao = religiao;
    }

    public Cargo getCargo ( ) {
        return cargo;
    }

    public void setCargo ( Cargo cargo ) {
        this.cargo = cargo;
    }

    public Quadro getQuadro ( ) {
        return quadro;
    }

    public void setQuadro ( Quadro quadro ) {
        this.quadro = quadro;
    }

    public Localidade getNaturalidade ( ) {
        return naturalidade;
    }

    public void setNaturalidade ( Localidade naturalidade ) {
        this.naturalidade = naturalidade;
    }
}
