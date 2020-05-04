package br.ufsm.csi.model;

public class Paciente extends Usuario {

    public Paciente( String nome, String email, String senha, boolean ativo, Permissao permissao) {
        super(nome, email, senha, ativo, permissao);
    }

    public Paciente(Usuario usuario){
        super(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.isAtivo(), usuario.getPermissao());
    }

}
