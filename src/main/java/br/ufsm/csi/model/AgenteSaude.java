package br.ufsm.csi.model;

/*

 CREATE TABLE agente_saude (
        id_agente_saude serial UNIQUE,
        id_usuario integer,
	 	especialidade varchar(30) not null,
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
        primary key (id_agente_saude)
        )

* */
public class AgenteSaude {
    private int id;
    private Usuario usuario;
    private String especialidade;

    public AgenteSaude(Usuario usuario){
        this.setUsuario(usuario);
    }

    public AgenteSaude(int id, Usuario usuario, String especialidade){
        this.setId(id);
        this.setUsuario(usuario);
        this.setEspecialidade(especialidade);
    }

    public AgenteSaude(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

