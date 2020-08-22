package br.ufsm.csi.controller;


import br.ufsm.csi.dao.AgenteSaudeDAO;
import br.ufsm.csi.dao.CheckSintomasDAO;
import br.ufsm.csi.dao.PacienteDAO;
import br.ufsm.csi.model.AgenteSaude;
import br.ufsm.csi.model.CheckSintomas;
import br.ufsm.csi.model.Paciente;
import br.ufsm.csi.service.CheckSintomasService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("checklist")
public class CheckSintomasController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = "/";

     //   if(req.getSession().getAttribute("logado") != null){

            String opcao = req.getParameter("gravar");

            if(opcao != null){

                //  public CheckSintomas(Paciente paciente, AgenteSaude agenteSaude, float temperatura, boolean tosse, boolean catarro,
                // boolean rouquidao, boolean dorGarganta, boolean narizEntupido, int qtdDiasSintomas) {

                int idPaciente = Integer.parseInt(req.getParameter("idpaciente"));
                int idagente = Integer.parseInt(req.getParameter("idagente"));
                float temp = Float.parseFloat(req.getParameter("temperatura"));
                boolean tosse = Boolean.parseBoolean(req.getParameter("tosse"));
                boolean rouquidao = Boolean.parseBoolean(req.getParameter("rouquidao"));
                boolean dorgarganta = Boolean.parseBoolean(req.getParameter("dorgarganta"));
                boolean narizentupido = Boolean.parseBoolean(req.getParameter("narizentupido"));
                boolean catarro = Boolean.parseBoolean(req.getParameter("catarro"));
                int diassintomas = Integer.parseInt(req.getParameter("diassintomas"));


                CheckSintomas cs = new CheckSintomas(
                        new PacienteDAO().getPaciente(idPaciente),
                        new AgenteSaudeDAO().getAgenteSaude(idagente),
                        temp, tosse, catarro, rouquidao, dorgarganta, narizentupido, diassintomas
                );
                String retorno = new CheckSintomasDAO().registrarCheckSintomas(cs);

                if(retorno.equals("OK")){
                    req.setAttribute("retorno", "checklist registrado com sucesso!");
                    req.setAttribute("suspeito", new CheckSintomasService().suspeitoCOVID(cs));
                }else {
                    req.setAttribute("retorno", "PROBLEMAS ao registar o checklist!");
                }



            }

            req.setAttribute("pacientes", new PacienteDAO().getPacientes());
            req.setAttribute("agentes", new AgenteSaudeDAO().getAgenteSaude());
            req.setAttribute("sintomas", new CheckSintomasDAO().getCheckSintomas());
            uri = "WEB-INF/jsp/checklist-covid.jsp";
      //  }


        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);

    }
}
