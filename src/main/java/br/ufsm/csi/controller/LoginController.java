package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UsuarioDAO;
import br.ufsm.csi.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("login")
public class LoginController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        System.out.println("email: "+email+" senha: "+senha);

        String uri = "";

        Usuario usuario = new UsuarioDAO().autenticar(email, senha);

        if(usuario != null){
            uri = "dashbord";

            HttpSession sessao = req.getSession();
            sessao.setAttribute("logado", usuario);

        }else{
            uri = "login";
            req.setAttribute("erro", "USUÁRIO OU SENHA INCORRETOS");
        }

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/"+uri+".jsp");
        rd.forward(req, resp);

    }
}
