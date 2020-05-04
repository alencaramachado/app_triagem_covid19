package br.ufsm.csi.controller;

import br.ufsm.csi.dao.PacienteDAO;
import br.ufsm.csi.model.Paciente;
import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("paciente-controller")
public class PacienteController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PacienteDAO dao = new PacienteDAO();
        String retorno = "";
        String opcao = req.getParameter("opcao");

        if(opcao.equals("excluir")){
            String id = req.getParameter("id");
            System.out.println("id paciente excluir: "+id);

            // excluir paciente ... implementar metodo no PacienteDAO
            retorno = dao.excluir(null);


        }else  if(opcao.equals("editar")){
            String id = req.getParameter("id");
            System.out.println("id paciente editar: "+id);

            // editar paciente ... implementar metodo no PacienteDAO
            retorno = dao.editar(null);

        }else {
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            Permissao p = new Permissao(3, "PACIENTE");
            Usuario usuario = new Usuario(nome,email,senha,true, p);
            Paciente paciente = new Paciente(usuario);
            retorno = dao.cadastrar(paciente);

        }

        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/");
        rd.forward(req, resp);


    }
}
