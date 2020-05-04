package br.ufsm.csi.model;


/*

CREATE TABLE public.permissao (
    id_permissao serial UNIQUE,
    nome_permissao character varying(50)
);

CREATE TABLE public.usuario_permissao (
    id serial UNIQUE,
    id_usuario integer,
    id_permissao integer,
	 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
     FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao)
);

insert into permissao (nome_permissao) values ('ADMIN');
insert into permissao (nome_permissao) values ('MEDICO');
insert into permissao (nome_permissao) values ('PACIENTE');


CREATE TABLE public.usuario (
    id_usuario serial UNIQUE,
    nome character varying(100) NOT NULL,
    email character varying(50) unique,
    senha character varying(400) NOT NULL,
    data_cadastro date,
    ativo boolean,
	primary key (id_usuario)
);

CREATE TABLE public.permissao (
    id_permissao serial UNIQUE,
    nome_permissao character varying(50)
);


CREATE TABLE public.usuario_permissao (
    id serial UNIQUE,
    id_usuario integer,
    id_permissao integer,
	 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
     FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao)
);

insert into permissao (nome_permissao) values ('ADMIN');
insert into permissao (nome_permissao) values ('MEDICO');
insert into permissao (nome_permissao) values ('PACIENTE');
drop table usuario_permissao
drop table permissao


* */
public class Permissao {

    private int id;
    private String nome;

    public Permissao() {
    }

    public Permissao(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
