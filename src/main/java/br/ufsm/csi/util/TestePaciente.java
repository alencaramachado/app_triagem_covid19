package br.ufsm.csi.util;

import br.ufsm.csi.dao.PacienteDAO;
import br.ufsm.csi.model.Paciente;
import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

public class TestePaciente {

    public static void main(String args[]){

        Permissao p = new Permissao(3, "PACIENTE");
        Usuario usuario = new Usuario("Flavio","flavio@teste","123",true, p);

        Paciente paciente = new Paciente(usuario);

        PacienteDAO dao = new PacienteDAO();
        dao.cadastrar(paciente);

        for(Paciente pa : dao.getPacientes()){
            System.out.println("nome: "+pa.getNome());
        }


    }

}
