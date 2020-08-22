package br.ufsm.csi.util;

import br.ufsm.csi.dao.AgenteSaudeDAO;
import br.ufsm.csi.dao.CheckSintomasDAO;
import br.ufsm.csi.dao.PacienteDAO;
import br.ufsm.csi.model.AgenteSaude;
import br.ufsm.csi.model.CheckSintomas;
import br.ufsm.csi.model.Paciente;

public class CheckSintomasTeste {

    public static void main(String args[]){
     //   registar();
        listar();
    }

    private static void listar(){
        for(CheckSintomas cs : new CheckSintomasDAO().getCheckSintomas()){
            System.out.println("Paciente: "+cs.getPaciente().getUsuario().getNome());
            System.out.println("Age: "+cs.getAgenteSaude().getUsuario().getNome());
            System.out.println("Catarro? "+cs.isCatarro());
        }
    }

    private static void registar(){
        Paciente p = new PacienteDAO().getPaciente(3);
        AgenteSaude as = new AgenteSaudeDAO().getAgenteSaude(1);

        CheckSintomas cs = new CheckSintomas(p, as, 41.8f, true, true,
                false, true, true, 10);
        String retorno = new CheckSintomasDAO().registrarCheckSintomas(cs);

    }

}
