package br.ufsm.csi.util;

import br.ufsm.csi.dao.ConectaDB;
import br.ufsm.csi.dao.UsuarioDAO;
import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

public class Teste {


    public static void main(String args[]){

       // testarCadastrarUsuario();
        testaGetUsuarios();

    }

    public static void testarCadastrarUsuario(){

        Permissao p = new Permissao(3, "PACIENTE");
       // Permissao p = new Permissao();

        Usuario usuario = new Usuario("Alencar","alencar@ufsm.br","1234",true, p);
        new UsuarioDAO().cadastrar(usuario);
    }

    public static void testaGetUsuarios(){
        for(Usuario u : new UsuarioDAO().getUsuarios()){
            System.out.println("nome: "+u.getNome());
            System.out.println("permissao: "+u.getPermissao().getNome());
        }
    }

}
