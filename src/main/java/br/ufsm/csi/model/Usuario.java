package br.ufsm.csi.model;

import java.sql.Date;
/*

CREATE TABLE public.usuario (
    id_usuario serial UNIQUE,
    nome character varying(100) NOT NULL,
    email character varying(50) unique,
    senha character varying(400) NOT NULL,
    data_cadastro date,
    ativo boolean,
	primary key (id_usuario)
);

* */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private Date data_cadastro;
    private boolean ativo;
    private Permissao permissao;


    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha, boolean ativo, Permissao permissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.permissao = permissao;
    }

    public Usuario(String nome, String email, String senha, boolean ativo, Permissao permissao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.permissao = permissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
