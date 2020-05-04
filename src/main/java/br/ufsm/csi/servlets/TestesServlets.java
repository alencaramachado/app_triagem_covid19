package br.ufsm.csi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("testes-servlets")
public class TestesServlets extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("recebeu uma request por get");

       String nome = req.getParameter("nome");
       String email = req.getParameter("email");
       String endereco = req.getParameter("endereco");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println(" <h1> Primeira servlet respondendo dentro de um método service! </h1>");
        out.println(" <h2>"+nome+" </h2>");
        out.println(" <h2>"+email+" </h2>");
        out.println(" <h2>"+endereco+" </h2>");
        out.println("</body>");
        out.println("</html>");


    }
}
