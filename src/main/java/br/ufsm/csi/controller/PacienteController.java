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

        String uri = "/";

       // if(req.getSession().getAttribute("logado") != null){

            PacienteDAO dao = new PacienteDAO();
            String retorno = "";
            String opcao = req.getParameter("opcao");

            if(opcao.equals("excluir")){
                String id = req.getParameter("id");
                System.out.println("id paciente excluir: "+id);

                // excluir paciente ... implementar metodo no PacienteDAO
                retorno = dao.excluir(null);


            }else  if(opcao.equals("editar")){
                int id = Integer.parseInt(req.getParameter("id"));
                System.out.println("id paciente editar: "+id);
                Paciente paciente = new PacienteDAO().getPaciente(id);

                req.setAttribute("paciente", paciente);

            }else {


                String nome = req.getParameter("nome");
                String email = req.getParameter("email");
                String senha = req.getParameter("senha");
                String cartaosus = req.getParameter("cartaosus");
                int idade = Integer.parseInt(req.getParameter("idade"));


                Permissao p = new Permissao(3, "PACIENTE");
                Usuario usuario = new Usuario(nome,email,senha,true, p);
                int id = Integer.parseInt(req.getParameter("idpaciente"));

                if(id > 0){
                    usuario.setId( Integer.parseInt(req.getParameter("idusuario")) );
                    Paciente paciente = new Paciente(id, usuario, cartaosus, idade);
                    retorno = dao.editar(paciente);

                }else{
                    Paciente paciente = new Paciente(usuario);
                    paciente.setCartaoSus(cartaosus);
                    paciente.setIdade(idade);
                    System.out.println("vai cadastrar paciente ...");
                    retorno = dao.cadastrar(paciente);
                }


            }
            req.setAttribute("retorno", retorno);
            uri = "WEB-INF/jsp/pacientes.jsp";
            req.setAttribute("pacientes", new PacienteDAO().getPacientes());
      //  }




        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);


    }
}
