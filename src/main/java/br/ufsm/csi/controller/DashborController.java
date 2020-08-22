package br.ufsm.csi.controller;

import br.ufsm.csi.dao.AgenteSaudeDAO;
import br.ufsm.csi.dao.CheckSintomasDAO;
import br.ufsm.csi.dao.PacienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("controlador")
public class DashborController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String uri = "/";

       // if(req.getSession().getAttribute("logado") != null){
            String opcao = req.getParameter("opcao");
            uri = "WEB-INF/jsp/dashbord.jsp";

            System.out.println("Id da sessão: ----> "+req.getSession().getId());

            if(opcao.equals("pacientes")){

                uri = "WEB-INF/jsp/pacientes.jsp";
                req.setAttribute("pacientes", new PacienteDAO().getPacientes());
            }else if(opcao.equals("checklist")){

                uri = "WEB-INF/jsp/checklist-covid.jsp";

                req.setAttribute("pacientes", new PacienteDAO().getPacientes());
                req.setAttribute("agentes", new AgenteSaudeDAO().getAgenteSaude());
                req.setAttribute("sintomas", new CheckSintomasDAO().getCheckSintomas());
            }else if(opcao.equals("sair")){
                req.getSession().invalidate();
                uri = "/";
            }

       // }

        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);

    }
}
